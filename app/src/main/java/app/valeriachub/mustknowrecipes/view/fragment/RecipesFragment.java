package app.valeriachub.mustknowrecipes.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.adapter.ViewPagerAdapter;
import app.valeriachub.mustknowrecipes.data.model.Cuisine;
import app.valeriachub.mustknowrecipes.data.model.TypeDish;
import app.valeriachub.mustknowrecipes.presenter.RecipesPresenter;
import app.valeriachub.mustknowrecipes.presenter.RecipesPresenterImpl;
import app.valeriachub.mustknowrecipes.utils.Const;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static app.valeriachub.mustknowrecipes.utils.Const.EXTRA_CUISINE;

public class RecipesFragment extends Fragment implements RecipesView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_toolbar_nav)
    ImageView toolbarNavView;
    @BindView(R.id.text_title)
    TextView titleView;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private Unbinder unbinder;
    private ViewPagerAdapter adapter;
    private RecipesPresenter recipesPresenter;
    private Cuisine cuisine;
    private List<TypeDish> typeDishList;

    public static RecipesFragment getInstance(Cuisine cuisine) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_CUISINE, cuisine);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && args.containsKey(Const.EXTRA_CUISINE)) {
            this.cuisine = args.getParcelable(EXTRA_CUISINE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_recipes, container, false);
        unbinder = ButterKnife.bind(this, view);
        initPresenter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initPresenter() {
        recipesPresenter = new RecipesPresenterImpl(getActivity(), this);
        initUI();
    }

    private void initUI() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleView.setText(cuisine.getTitle());
        toolbarNavView.setImageResource(R.drawable.ic_back);
        recipesPresenter.getTypeDish();
    }

    private void setViewPager() {
        adapter = new ViewPagerAdapter(getFragments(), getFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < typeDishList.size(); i++) {
            fragments.add(RecipesByTypeFragment.getInstance(cuisine.getId(), typeDishList.get(i).getId()));
        }
        return fragments;
    }

    private void setTabs() {
        for (int i = 0; i < typeDishList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < typeDishList.size(); i++) {
            tabLayout.getTabAt(i).setText(typeDishList.get(i).getTitle().toUpperCase());
        }
    }

    @Override
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @Override
    public void setDishesType(List<TypeDish> list) {
        typeDishList = list;
        setViewPager();
        setTabs();
    }

    @OnClick(R.id.image_toolbar_nav)
    void onBackClick() {
        recipesPresenter.onItemClicked(R.id.image_toolbar_nav);
    }
}
