package app.valeriachub.mustknowrecipes.presenter;

import app.valeriachub.mustknowrecipes.data.model.Cuisine;

public interface CuisinesPresenter {

    void getCountries();

    void onItemClicked(int id);

    void onCountryClicked(Cuisine cuisine);
}
