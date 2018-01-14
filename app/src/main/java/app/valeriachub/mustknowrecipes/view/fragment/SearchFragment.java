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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.adapter.FavouritesAdapter;
import app.valeriachub.mustknowrecipes.callback.RecipeCallback;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.presenter.SearchPresenter;
import app.valeriachub.mustknowrecipes.presenter.SearchPresenterImpl;
import app.valeriachub.mustknowrecipes.utils.KeyboardUtils;
import app.valeriachub.mustknowrecipes.view.activity.MainActivity;
import app.valeriachub.mustknowrecipes.view.activity.RecipeDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class SearchFragment extends Fragment implements SearchView, RecipeCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_toolbar_nav)
    ImageView toolbarNavView;
    @BindView(R.id.text_title)
    TextView titleView;
    @BindView(R.id.edit_search)
    EditText searchView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private MainActivity mainActivity;
    private SearchPresenter searchPresenter;
    private FavouritesAdapter adapter;

    private List<Recipe> recipeList = new ArrayList<>();
    private List<Recipe> searchList = new ArrayList<>();

    public static SearchFragment getInstance() {
        return new SearchFragment();
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
        View view = inflater.inflate(R.layout.fr_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initUI() {
        searchPresenter = new SearchPresenterImpl(getActivity(), this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarNavView.setImageResource(R.drawable.ic_more);
        titleView.setText(getString(R.string.search));
        searchPresenter.getRecipes();
    }

    @OnClick(R.id.image_toolbar_nav)
    void onMoreClicked(View view) {
        searchPresenter.onItemClicked(view.getId());
    }

    @OnTextChanged(value = R.id.edit_search, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onSearchChanged(CharSequence text) {
        searchPresenter.onSearchChanged(recipeList, text.toString());
    }

    /**
     * Override View
     */

    @Override
    public void showDrawer() {
        KeyboardUtils.hideKeyboard(getActivity(), searchView);
        if (mainActivity != null) {
            mainActivity.showDrawer();
        }
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {
        recipeList.addAll(recipes);
        searchList.addAll(recipes);
        adapter = new FavouritesAdapter(searchList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setSearchRecipes(List<Recipe> recipes) {
        searchList.clear();
        searchList.addAll(recipes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showRecipeDetails(RecipeFull recipe) {
        RecipeDetailsActivity.start(getActivity(), recipe);
    }

    @Override
    public void notifyItemChanged(int position) {
//        adapter.notifyItemChanged(position);
    }

    /**
     * Override Callback
     */

    @Override
    public void onRecipeClicked(Recipe recipe) {
        searchPresenter.onRecipeClicked(recipe);
    }

    @Override
    public void onRecipeLikeClicked(Recipe recipe, int position) {
        searchPresenter.onLikeRecipeClicked(recipe, position);
    }
}
