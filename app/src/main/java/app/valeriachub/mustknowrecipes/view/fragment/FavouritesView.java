package app.valeriachub.mustknowrecipes.view.fragment;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;

public interface FavouritesView {

    void setFavouritesRecipes(List<Recipe> recipes);

    void showNoRecipes();

    void showDrawer();

    void notifyRecipeRemoved(int position);

    void showSelectedRecipe(RecipeFull recipeFull);
}
