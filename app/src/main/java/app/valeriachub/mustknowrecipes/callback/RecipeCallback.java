package app.valeriachub.mustknowrecipes.callback;

import app.valeriachub.mustknowrecipes.data.model.Recipe;

public interface RecipeCallback {

    void onRecipeClicked(Recipe recipe);

    void onRecipeLikeClicked(Recipe recipe, int position);
}
