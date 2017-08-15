package app.valeriachub.mustknowrecipes.presenter;

import android.content.Context;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.DataManager;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.view.fragment.FavouritesView;

public class FavouritesPresenterImpl implements FavouritesPresenter {

    private Context context;
    private FavouritesView favouritesView;

    public FavouritesPresenterImpl(Context context, FavouritesView favouritesView) {
        this.favouritesView = favouritesView;
        this.context = context;
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case R.id.image_toolbar_nav: {
                favouritesView.showDrawer();
                break;
            }
        }
    }

    @Override
    public void getFavouriteRecipes() {
        List<Recipe> list;
        DataManager dataManager = DataManager.getInstance(context);
        list = dataManager.getFavouriteList();
        if (list.size() > 0) {
            favouritesView.setFavouritesRecipes(list);
        } else {
            favouritesView.showNoRecipes();
        }
    }

    @Override
    public void removeFromFavourites(Recipe recipe, List<Recipe> recipeList, int position) {
        DataManager.getInstance(context).removeFromFavourites(recipe.getId());
        recipeList.remove(position);
        favouritesView.notifyRecipeRemoved(position);
        if(recipeList.size() == 0){
            favouritesView.showNoRecipes();
        }
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        DataManager dataManager = DataManager.getInstance(context);
        RecipeFull recipeFull = dataManager.getRecipeFull(recipe);
        favouritesView.showSelectedRecipe(recipeFull);
    }
}
