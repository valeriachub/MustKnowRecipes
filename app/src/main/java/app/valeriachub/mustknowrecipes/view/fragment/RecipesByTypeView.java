package app.valeriachub.mustknowrecipes.view.fragment;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;

public interface RecipesByTypeView {
    void showSelectedDish(RecipeFull recipeFull);

    void setDishes(List<Recipe> list);

    void showNoRecipesText();

    void notifyRecipeChanged(int position);
}
