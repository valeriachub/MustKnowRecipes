package app.valeriachub.mustknowrecipes.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PictureUtils {

    public static void setImage(Context context, ImageView view, String pictureTitle) {
        int picture = context.getResources().getIdentifier(pictureTitle, "drawable", context.getPackageName());
        if (picture > 1) {
            Glide
                    .with(context)
                    .load(picture)
                    .into(view);
        }
    }

    public static void setIcon(Context context, ImageView view, int pictureIcon) {
        Glide
                .with(context)
                .load(pictureIcon)
                .into(view);

    }
}
