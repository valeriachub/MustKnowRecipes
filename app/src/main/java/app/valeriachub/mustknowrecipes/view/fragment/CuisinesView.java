package app.valeriachub.mustknowrecipes.view.fragment;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Cuisine;

public interface CuisinesView {

    void showDrawer();

    void showSelectedCountry(Cuisine cuisine);

    void setCountries(List<Cuisine> list);
}
