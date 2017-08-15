package app.valeriachub.mustknowrecipes.view.fragment;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.TypeDish;

public interface RecipesView {

    void onBackClicked();

    void setDishesType(List<TypeDish> list);
}
