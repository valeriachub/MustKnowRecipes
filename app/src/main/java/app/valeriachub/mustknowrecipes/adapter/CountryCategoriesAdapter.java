package app.valeriachub.mustknowrecipes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.model.Cuisine;
import app.valeriachub.mustknowrecipes.utils.PictureUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryCategoriesAdapter extends RecyclerView.Adapter<CountryCategoriesAdapter.ViewHolder> {

    public interface CountryCategoriesCallback {
        void onCountryClicked(Cuisine cuisine);
    }

    private Context context;
    private List<Cuisine> countries;
    private CountryCategoriesCallback callback;

    public CountryCategoriesAdapter(List<Cuisine> countries, CountryCategoriesCallback callback) {
        this.countries = countries;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.li_cuisine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cuisine cuisine = countries.get(position);
        holder.titleView.setText(cuisine.getTitle());
        PictureUtils.setImage(context, holder.imageView, cuisine.getPicture());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_cuisine)
        ImageView imageView;
        @BindView(R.id.text_title)
        TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item)
        void onItemClicked() {
            if (callback != null) {
                callback.onCountryClicked(countries.get(getAdapterPosition()));
            }
        }
    }
}
