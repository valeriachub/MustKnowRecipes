package app.valeriachub.mustknowrecipes.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.model.Item;
import app.valeriachub.mustknowrecipes.data.model.RecipeFull;
import app.valeriachub.mustknowrecipes.data.model.Step;
import app.valeriachub.mustknowrecipes.presenter.RecipeDetailsPresenter;
import app.valeriachub.mustknowrecipes.presenter.RecipeDetailsPresenterImpl;
import app.valeriachub.mustknowrecipes.utils.Const;
import app.valeriachub.mustknowrecipes.utils.PictureUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsView {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.text_description)
    TextView descriptionTextView;

    @BindView(R.id.layout_ingredients)
    LinearLayout ingredientsLayout;
    @BindView(R.id.layout_steps)
    LinearLayout stepsLayout;

    private RecipeFull recipeFull;
    private Unbinder unbinder;

    private RecipeDetailsPresenter detailsPresenter;

    public static void start(Context context, RecipeFull recipeFull) {
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra(Const.EXTRA_RECIPE, recipeFull);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_recipe_details);
        unbinder = ButterKnife.bind(this);
        getExtras();
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void getExtras() {
        Bundle args = getIntent().getExtras();
        if (args != null && args.containsKey(Const.EXTRA_RECIPE)) {
            this.recipeFull = args.getParcelable(Const.EXTRA_RECIPE);
        }
    }

    private void initUI() {
        detailsPresenter = new RecipeDetailsPresenterImpl(this);
        detailsPresenter.getRecipeDetails(recipeFull);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(recipeFull.getTitle());
    }

    private void setIngredients(List<Item> items) {
        for (Item item : items) {
            View view = LayoutInflater.from(this).inflate(R.layout.li_step, null);

            TextView number = (TextView) view.findViewById(R.id.text_number);
            TextView description = (TextView) view.findViewById(R.id.text_description);
            TextView quantity = (TextView) view.findViewById(R.id.text_quantity);
            description.setTypeface(null, Typeface.BOLD);
            quantity.setVisibility(View.VISIBLE);

            number.setText(String.valueOf(item.getNumber()));
            description.setText(item.getTitle());
            quantity.setText(item.getQuantity());

            ingredientsLayout.addView(view);
        }
    }

    private void setSteps(List<Step> steps) {
        for (Step step : steps) {
            View view = LayoutInflater.from(this).inflate(R.layout.li_step, null);

            TextView number = (TextView) view.findViewById(R.id.text_number);
            TextView description = (TextView) view.findViewById(R.id.text_description);

            number.setText(String.valueOf(step.getNumber()));
            description.setText(step.getText());

            stepsLayout.addView(view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        detailsPresenter.onItemClicked(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    /**
     * Override View
     */

    @Override
    public void onBackClicked() {
        onBackPressed();
    }

    @Override
    public void showDescription(String description) {
        descriptionTextView.setVisibility(View.VISIBLE);
        descriptionTextView.setText(description);
    }

    @Override
    public void showSteps(List<Step> steps) {
        setSteps(steps);
    }

    @Override
    public void showItems(List<Item> items) {
        setIngredients(items);
    }

    @Override
    public void showCoverPicture(String coverPicture) {
        PictureUtils.setImage(this, imageView, coverPicture);
    }
}
