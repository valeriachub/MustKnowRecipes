package app.valeriachub.mustknowrecipes.view.activity;

import java.util.List;

import app.valeriachub.mustknowrecipes.data.model.Item;
import app.valeriachub.mustknowrecipes.data.model.Step;

public interface RecipeDetailsView {
    void onBackClicked();

    void showDescription(String description);

    void showSteps(List<Step> steps);

    void showItems(List<Item> items);

    void showCoverPicture(String coverPicture);
}
