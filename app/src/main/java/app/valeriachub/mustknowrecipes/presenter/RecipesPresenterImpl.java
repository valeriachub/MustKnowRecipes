package app.valeriachub.mustknowrecipes.presenter;

import android.content.Context;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.DataManager;
import app.valeriachub.mustknowrecipes.data.model.TypeDish;
import app.valeriachub.mustknowrecipes.view.fragment.RecipesView;

public class RecipesPresenterImpl implements RecipesPresenter {

    private RecipesView recipesView;
    private Context context;

    public RecipesPresenterImpl(Context context, RecipesView recipesView) {
        this.recipesView = recipesView;
        this.context = context;
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case R.id.image_toolbar_nav: {
                recipesView.onBackClicked();
                break;
            }
        }
    }

    @Override
    public void getTypeDish() {
        List<TypeDish> list = null;
        DataManager dataManager = DataManager.getInstance(context);
        list = dataManager.getTypeDishList();
        recipesView.setDishesType(list);
    }
}
