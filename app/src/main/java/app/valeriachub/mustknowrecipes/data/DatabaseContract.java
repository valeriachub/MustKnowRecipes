package app.valeriachub.mustknowrecipes.data;

public class DatabaseContract {
    public DatabaseContract() {
    }

    public static final class Recipe {
        public final static String TABLE_NAME = "Recipe";

        public final static String _ID = "_id";
        public final static String TITLE = "title";
        public final static String PICTURE = "picture";
        public final static String TIME_COOKING = "time_cooking";
        public final static String ID_CUISINE = "id_cuisine";
        public final static String ID_TYPE = "id_type";
        public final static String DESCRIPTION = "description";
    }

    public static final class Cuisine {
        public final static String TABLE_NAME = "Cuisine";

        public final static String _ID = "_id";
        public final static String TITLE = "title";
        public final static String PICTURE = "picture";
    }

    public static final class TypeDish {
        public final static String TABLE_NAME = "TypeDish";

        public final static String _ID = "_id";
        public final static String TITLE = "title";
    }

    public static final class Step {
        public final static String TABLE_NAME = "Step";

        public final static String _ID = "_id";
        public final static String TEXT = "text";
        public final static String PICTURE = "picture";
    }

    public static final class StepList {
        public final static String TABLE_NAME = "StepList";

        public final static String _ID = "_id";
        public final static String ID_RECIPE = "id_recipe";
        public final static String ID_STEP = "id_step";
    }

    public static final class Item {
        public final static String TABLE_NAME = "Item";

        public final static String _ID = "_id";
        public final static String NUMBER = "number";
        public final static String TITLE = "title";
        public final static String QUANTITY = "quantity";
    }

    public static final class ItemList {
        public final static String TABLE_NAME = "ItemList";

        public final static String _ID = "_id";
        public final static String ID_RECIPE = "id_recipe";
        public final static String ID_ITEM = "id_item";
    }

    public static final class FavouriteRecipe {
        public final static String TABLE_NAME = "FavouriteRecipe";

        public final static String _ID = "_id";
        public final static String ID_RECIPE = "id_recipe";
    }
}
