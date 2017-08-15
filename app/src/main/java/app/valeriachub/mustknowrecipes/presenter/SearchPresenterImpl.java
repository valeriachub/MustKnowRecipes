package app.valeriachub.mustknowrecipes.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.DataManager;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.view.fragment.SearchView;

public class SearchPresenterImpl implements SearchPresenter {

    private Context context;
    private SearchView searchView;

    public SearchPresenterImpl(Context context, SearchView searchView) {
        this.searchView = searchView;
        this.context = context;
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case R.id.image_toolbar_nav: {
                searchView.showDrawer();
                break;
            }
        }
    }

    @Override
    public void getRecipes() {
        List<Recipe> recipes;
        DataManager dataManager = DataManager.getInstance(context);
        recipes = dataManager.getRecipeListAll();
        searchView.setRecipes(recipes);
    }

    @Override
    public void onSearchChanged(List<Recipe> recipes, String title) {
        List<Recipe> searchList = new ArrayList<>();
        for(Recipe recipe: recipes){
            if(recipe.getTitle().toLowerCase().contains(title.toLowerCase())){
                searchList.add(recipe);
            }
        }
        searchView.setSearchRecipes(searchList);
    }
}
