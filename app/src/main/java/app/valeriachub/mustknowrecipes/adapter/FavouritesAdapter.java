package app.valeriachub.mustknowrecipes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.callback.RecipeCallback;
import app.valeriachub.mustknowrecipes.data.model.Recipe;
import app.valeriachub.mustknowrecipes.utils.PictureUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private RecipeCallback callback;

    public FavouritesAdapter(List<Recipe> recipeList, RecipeCallback callback) {
        this.recipeList = recipeList;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.li_favourite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        PictureUtils.setImage(context, holder.imageView, recipe.getPicture());
        holder.titleView.setText(recipe.getTitle());
        holder.timeView.setText(recipe.getTimeCooking() + " " + context.getString(R.string.mins));
        if (recipe.getIdFavourite() > 0) {
            PictureUtils.setIcon(context, holder.likeView, R.drawable.ic_favorite);
        } else {
            PictureUtils.setIcon(context, holder.likeView, R.drawable.ic_no_favorite);
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item)
        LinearLayout itemView;
        @BindView(R.id.image_dish)
        ImageView imageView;
        @BindView(R.id.image_like)
        ImageView likeView;
        @BindView(R.id.text_title)
        TextView titleView;
        @BindView(R.id.text_time)
        TextView timeView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.image_like)
        void onLikeClicked() {
            if (callback != null) {
                callback.onRecipeLikeClicked(recipeList.get(getAdapterPosition()), getAdapterPosition());
            }
        }

        @OnClick(R.id.item)
        void onRecipeClicked() {
            if (callback != null) {
                callback.onRecipeClicked(recipeList.get(getAdapterPosition()));
            }
        }
    }
}
