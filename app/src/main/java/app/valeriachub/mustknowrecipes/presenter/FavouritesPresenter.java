package app.valeriachub.mustknowrecipes.presenter;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Recipe;

public interface FavouritesPresenter {

    void onItemClicked(int id);

    void getFavouriteRecipes();

    void removeFromFavourites(Recipe recipe, List<Recipe> recipeList, int position);

    void onRecipeClicked(Recipe recipe);
}
