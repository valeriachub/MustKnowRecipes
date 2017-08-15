package app.valeriachub.mustknowrecipes.presenter;

import android.content.Context;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.DataManager;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.view.fragment.RecipesByTypeView;

public class RecipesByTypePresenterImpl implements RecipesByTypePresenter {

    private RecipesByTypeView dishesCategoryView;
    private Context context;

    public RecipesByTypePresenterImpl(Context context, RecipesByTypeView dishesCategoryView) {
        this.dishesCategoryView = dishesCategoryView;
        this.context = context;
    }

    @Override
    public void onDishClicked(Recipe recipe) {
        DataManager dataManager = DataManager.getInstance(context);
        RecipeFull recipeFull = dataManager.getRecipeFull(recipe);
        dishesCategoryView.showSelectedDish(recipeFull);
    }

    @Override
    public void onLikeRecipeClicked(Recipe recipe, int position) {
        DataManager dataManager = DataManager.getInstance(context);
        if (recipe.getIdFavourite() > 0) {
            dataManager.removeFromFavourites(recipe.getId());
            recipe.setIdFavourite(0);
        } else {
            recipe.setIdFavourite(dataManager.addToFavourites(recipe.getId()));
        }
        dishesCategoryView.notifyRecipeChanged(position);
    }

    @Override
    public void getDishes(int idCuisine, int idType) {
        List<Recipe> list;
        DataManager dataManager = DataManager.getInstance(context);
        list = dataManager.getRecipeList(idCuisine, idType);
        if (list.size() > 0) {
            dishesCategoryView.setDishes(list);
        } else {
            dishesCategoryView.showNoRecipesText();
        }
    }
}
