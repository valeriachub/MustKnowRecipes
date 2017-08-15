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
import android.widget.TextView;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.adapter.CountryCategoriesAdapter;
import app.valeriachub.mustknowrecipes.data.model.Cuisine;
import app.valeriachub.mustknowrecipes.presenter.CuisinesPresenter;
import app.valeriachub.mustknowrecipes.presenter.CuisinesPresenterImpl;
import app.valeriachub.mustknowrecipes.utils.FragmentUtils;
import app.valeriachub.mustknowrecipes.view.activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CuisinesFragment extends Fragment implements CuisinesView, CountryCategoriesAdapter.CountryCategoriesCallback {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_toolbar_nav)
    ImageView toolbarNavView;
    @BindView(R.id.text_title)
    TextView titleView;

    private Unbinder unbinder;
    private CountryCategoriesAdapter adapter;
    private List<Cuisine> cuisineList;
    private CuisinesPresenter cuisinesPresenter;
    private MainActivity mainActivity;

    public static CuisinesFragment getInstance() {
        return new CuisinesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View view = inflater.inflate(R.layout.fr_country_categories, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainActivity != null) {
            mainActivity.unblockDrawer();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initUI() {
        cuisinesPresenter = new CuisinesPresenterImpl(getActivity(), this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarNavView.setImageResource(R.drawable.ic_more);
        titleView.setText(getString(R.string.cuisines));
        cuisinesPresenter.getCountries();
    }

    @OnClick(R.id.image_toolbar_nav)
    void onMoreClicked() {
        cuisinesPresenter.onItemClicked(R.id.image_toolbar_nav);
    }

    /**
     * Override View
     */

    @Override
    public void showDrawer() {
        if (mainActivity == null) return;
        mainActivity.openDrawer();
    }

    @Override
    public void showSelectedCountry(Cuisine cuisine) {
        FragmentUtils.replace(getFragmentManager(), R.id.layout_container, RecipesFragment.getInstance(cuisine), "", true);
        if (mainActivity != null) {
            mainActivity.blockDrawer();
        }
    }

    @Override
    public void setCountries(List<Cuisine> list) {
        cuisineList = list;
        adapter = new CountryCategoriesAdapter(cuisineList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    /**
     * Override Callback
     */

    @Override
    public void onCountryClicked(Cuisine cuisine) {
        cuisinesPresenter.onCountryClicked(cuisine);
    }
}
