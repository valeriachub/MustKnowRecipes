package app.valeriachub.mustknowrecipes.presenter;

import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.view.activity.RecipeDetailsView;

public class RecipeDetailsPresenterImpl implements RecipeDetailsPresenter {
    private RecipeDetailsView recipeDetailsView;

    public RecipeDetailsPresenterImpl(RecipeDetailsView recipeDetailsView) {
        this.recipeDetailsView = recipeDetailsView;
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case android.R.id.home: {
                recipeDetailsView.onBackClicked();
                break;
            }
        }
    }

    @Override
    public void getRecipeDetails(RecipeFull recipeFull) {
        //TODO check the logic

        if (recipeFull != null) {
            recipeDetailsView.showCoverPicture(recipeFull.getPicture());
            recipeDetailsView.showItems(recipeFull.getItemList());
            recipeDetailsView.showSteps(recipeFull.getStepList());

            if(!recipeFull.getDescription().isEmpty()){
                recipeDetailsView.showDescription(recipeFull.getDescription());
            }
        }
    }
}
