package app.valeriachub.mustknowrecipes.view.fragment;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Recipe;

public interface SearchView {

    void showDrawer();

    void setRecipes(List<Recipe> recipes);

    void setSearchRecipes(List<Recipe> recipes);
}
