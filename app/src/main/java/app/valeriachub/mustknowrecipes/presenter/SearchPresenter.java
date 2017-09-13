package app.valeriachub.mustknowrecipes.presenter;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Recipe;

public interface SearchPresenter {

    void onItemClicked(int id);

    void getRecipes();

    void onSearchChanged(List<Recipe> recipes, String title);

    void onLikeRecipeClicked(Recipe recipe);

    void onRecipeClicked(Recipe recipe);
}
