package app.valeriachub.mustknowrecipes.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.adapter.FavouritesAdapter;
import app.valeriachub.mustknowrecipes.callback.RecipeCallback;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.presenter.FavouritesPresenter;
import app.valeriachub.mustknowrecipes.presenter.FavouritesPresenterImpl;
import app.valeriachub.mustknowrecipes.view.activity.MainActivity;
import app.valeriachub.mustknowrecipes.view.activity.RecipeDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FavouritesFragment extends Fragment implements FavouritesView, RecipeCallback {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_toolbar_nav)
    ImageView toolbarNavView;
    @BindView(R.id.text_title)
    TextView titleView;
    @BindView(R.id.layout_no_recipes)
    LinearLayout noRecipesLayout;

    private Unbinder unbinder;
    private MainActivity mainActivity;
    private FavouritesPresenter favouritesPresenter;
    private FavouritesAdapter adapter;
    private List<Recipe> recipeList;

    public static FavouritesFragment getInstance() {
        return new FavouritesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainActivity = (MainActivity) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_favourite, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initUI() {
        favouritesPresenter = new FavouritesPresenterImpl(getActivity(), this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarNavView.setImageResource(R.drawable.ic_more);
        titleView.setText(getString(R.string.favourite));
        favouritesPresenter.getFavouriteRecipes();
    }

    @OnClick(R.id.image_toolbar_nav)
    void onMoreClicked(View view) {
        favouritesPresenter.onItemClicked(view.getId());
    }

    /**
     * Override View
     */

    @Override
    public void setFavouritesRecipes(List<Recipe> recipes) {
        recipeList = recipes;
        adapter = new FavouritesAdapter(recipeList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNoRecipes() {
        noRecipesLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDrawer() {
        if (mainActivity != null) {
            mainActivity.showDrawer();
        }
    }

    @Override
    public void notifyRecipeRemoved(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void showSelectedRecipe(RecipeFull recipeFull) {
        RecipeDetailsActivity.start(getActivity(), recipeFull);
    }

    /**
     * Override Callback
     */

    @Override
    public void onRecipeClicked(Recipe recipe) {
        favouritesPresenter.onRecipeClicked(recipe);
    }

    @Override
    public void onRecipeLikeClicked(Recipe recipe, int position) {
        favouritesPresenter.removeFromFavourites(recipe, recipeList, position);
    }
}
