package app.valeriachub.mustknowrecipes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Cuisine;
import app.valeriachub.mustknowrecipes.data.model.Item;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.data.model.Step;
import app.valeriachub.mustknowrecipes.data.model.TypeDish;

public class DataManager {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private static DataManager dataManager = null;

    public static DataManager getInstance(Context context) {
        if (dataManager == null) {
            dataManager = new DataManager(context);
        }
        return dataManager;
    }

    private DataManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.createDb();
    }

    /**
     * Cuisine
     */

    public List<Cuisine> getCuisineList() {
        List<Cuisine> list = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseContract.Cuisine.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                Cuisine cuisine = new Cuisine(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                list.add(cuisine);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    /**
     * TypeDish
     */

    public List<TypeDish> getTypeDishList() {
        List<TypeDish> list = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseContract.TypeDish.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                TypeDish typeDish = new TypeDish(cursor.getInt(0), cursor.getString(1));
                list.add(typeDish);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * Recipe
     */

    public List<Recipe> getRecipeList(int idCuisine, int idTypeDish) {
        List<Recipe> list = new ArrayList<>();
        String query = "SELECT Recipe._id, Recipe.title, Recipe.picture, Recipe.time_cooking, Cuisine.title AS cuisine_title, TypeDish.title AS typedish_title, Recipe.description, FavouriteRecipe._id AS favourite \n" +
                "FROM Recipe \n" +
                "INNER JOIN Cuisine ON Recipe.id_cuisine = Cuisine._id \n" +
                "INNER JOIN TypeDish ON Recipe.id_type = TypeDish._id \n" +
                "LEFT JOIN FavouriteRecipe  ON FavouriteRecipe.id_recipe =  Recipe._id \n" +
                "WHERE Cuisine._id = ? AND TypeDish._id =? " +
                "ORDER BY Recipe.title;";


        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(idCuisine), String.valueOf(idTypeDish)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Recipe recipe = new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public RecipeFull getRecipeFull(Recipe recipe) {
        RecipeFull recipeFull = new RecipeFull(recipe);
        recipeFull.setItemList(getItemList(recipe.getId()));
        recipeFull.setStepList(getStepList(recipe.getId()));
        return recipeFull;
    }

    public List<Recipe> getRecipeListAll() {
        List<Recipe> list = new ArrayList<>();
        String query = "SELECT Recipe._id, Recipe.title, Recipe.picture, Recipe.time_cooking, Cuisine.title AS cuisine_title, TypeDish.title AS typedish_title, Recipe.description, FavouriteRecipe._id AS favourite \n" +
                "FROM Recipe \n" +
                "INNER JOIN Cuisine ON Recipe.id_cuisine = Cuisine._id \n" +
                "INNER JOIN TypeDish ON Recipe.id_type = TypeDish._id \n" +
                "LEFT JOIN FavouriteRecipe  ON FavouriteRecipe.id_recipe =  Recipe._id " +
                "ORDER BY Recipe.title;";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Recipe recipe = new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * Item
     */

    public List<Item> getItemList(int idRecipe) {
        List<Item> list = new ArrayList<>();
        String query = "SELECT Item._id, Item.number, Item.title, Item.quantity \n" +
                "FROM ItemList \n" +
                "JOIN Item ON ItemList.id_item = Item._id \n" +
                "WHERE ItemList.id_recipe = ?;";

        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(idRecipe)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Item item = new Item(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


    /**
     * Step
     */

    public List<Step> getStepList(int idRecipe) {
        List<Step> list = new ArrayList<>();
        String query = "SELECT Step._id, Step.number, Step.text, Step.picture \n" +
                "FROM StepList \n" +
                "INNER JOIN Step ON StepList.id_step = Step._id \n" +
                "WHERE StepList.id_recipe = ?;";

        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(idRecipe)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Step step = new Step(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                list.add(step);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * FavouriteRecipe
     */

    public int addToFavourites(int idRecipe) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FavouriteRecipe.ID_RECIPE, idRecipe);
        return (int) database.insert(DatabaseContract.FavouriteRecipe.TABLE_NAME, null, values);
    }

    public void removeFromFavourites(int idRecipe) {
        database.delete(DatabaseContract.FavouriteRecipe.TABLE_NAME, DatabaseContract.FavouriteRecipe.ID_RECIPE + " = ?", new String[]{String.valueOf(idRecipe)});
    }

    public List<Recipe> getFavouriteList() {
        List<Recipe> list = new ArrayList<>();
        String query = "SELECT Recipe._id, Recipe.title, Recipe.picture, Recipe.time_cooking, Cuisine.title AS cuisine_title, TypeDish.title AS typedish_title, Recipe.description , FavouriteRecipe._id AS favourite\n" +
                "FROM Recipe\n" +
                "INNER JOIN Cuisine ON Recipe.id_cuisine = Cuisine._id \n" +
                "INNER JOIN TypeDish ON Recipe.id_type = TypeDish._id \n" +
                "INNER JOIN FavouriteRecipe  ON Recipe._id = FavouriteRecipe.id_recipe " +
                "ORDER BY Recipe.title;";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Recipe recipe = new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
