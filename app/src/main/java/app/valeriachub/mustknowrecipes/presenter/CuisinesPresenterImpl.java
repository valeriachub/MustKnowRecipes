package app.valeriachub.mustknowrecipes.presenter;

import android.content.Context;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.DataManager;
import app.valeriachub.mustknowrecipes.data.model.Cuisine;
import app.valeriachub.mustknowrecipes.view.fragment.CuisinesView;

public class CuisinesPresenterImpl implements CuisinesPresenter {

    private CuisinesView cuisinesView;
    private Context context;

    public CuisinesPresenterImpl(Context context, CuisinesView cuisinesView) {
        this.cuisinesView = cuisinesView;
        this.context = context;
    }

    @Override
    public void getCountries() {
        List<Cuisine> list;
        DataManager dataManager = DataManager.getInstance(context);
        list = dataManager.getCuisineList();
        cuisinesView.setCountries(list);
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case R.id.image_toolbar_nav: {
                cuisinesView.showDrawer();
                break;
            }
        }
    }

    @Override
    public void onCountryClicked(Cuisine cuisine) {
        cuisinesView.showSelectedCountry(cuisine);
    }
}
