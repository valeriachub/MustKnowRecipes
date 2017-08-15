package app.valeriachub.mustknowrecipes.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.adapter.DishesAdapter;
import app.valeriachub.mustknowrecipes.callback.RecipeCallback;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.presenter.RecipesByTypePresenter;
import app.valeriachub.mustknowrecipes.presenter.RecipesByTypePresenterImpl;
import app.valeriachub.mustknowrecipes.utils.Const;
import app.valeriachub.mustknowrecipes.view.activity.RecipeDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipesByTypeFragment extends Fragment implements RecipesByTypeView, RecipeCallback {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_no_recipes)
    LinearLayout noRecipesLayout;

    private Unbinder unbinder;
    private RecipesByTypePresenter recipesByTypePresenter;
    private DishesAdapter adapter;
    private int idCuisine;
    private int idType;

    public static RecipesByTypeFragment getInstance(int idCuisine, int idType) {
        RecipesByTypeFragment fragment = new RecipesByTypeFragment();
        Bundle args = new Bundle();
        args.putInt(Const.EXTRA_CUISINE, idCuisine);
        args.putInt(Const.EXTRA_TYPE, idType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && args.containsKey(Const.EXTRA_CUISINE) && args.containsKey(Const.EXTRA_TYPE)) {
            this.idCuisine = args.getInt(Const.EXTRA_CUISINE);
            this.idType = args.getInt(Const.EXTRA_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_recipes_by_type, container, false);
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
        recipesByTypePresenter = new RecipesByTypePresenterImpl(getActivity(), this);
        recipesByTypePresenter.getDishes(idCuisine, idType);
    }

    /**
     * Override Callback
     */

    @Override
    public void onRecipeClicked(Recipe recipe) {
        recipesByTypePresenter.onDishClicked(recipe);
    }

    @Override
    public void onRecipeLikeClicked(Recipe recipe, int position) {
        recipesByTypePresenter.onLikeRecipeClicked(recipe, position);
    }

    /**
     * Override View
     */

    @Override
    public void showSelectedDish(RecipeFull recipeFull) {
        RecipeDetailsActivity.start(getActivity(), recipeFull);
    }

    @Override
    public void setDishes(List<Recipe> list) {
        adapter = new DishesAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNoRecipesText() {
        noRecipesLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyRecipeChanged(int position) {
        //TODO Jump Jump
        adapter.notifyItemChanged(position);
    }
}
