package app.valeriachub.mustknowrecipes.presenter;

import app.valeriachub.mustknowrecipes.data.model.Recipe;

public interface RecipesByTypePresenter {

    void onDishClicked(Recipe recipe);

    void onLikeRecipeClicked(Recipe recipe, int position);

    void getDishes(int idCuisine, int idType);
}
