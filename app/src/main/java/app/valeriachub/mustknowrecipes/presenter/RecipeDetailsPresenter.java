package app.valeriachub.mustknowrecipes.presenter;

import app.valeriachub.mustknowrecipes.data.model.RecipeFull;

public interface RecipeDetailsPresenter {

    void onItemClicked(int id);

    void getRecipeDetails(RecipeFull recipeFull);
}
