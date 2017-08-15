package app.valeriachub.mustknowrecipes.utils;

import app.valeriachub.mustknowrecipes.R;

public class Enums {

    public enum Country {
        FRANCE(0, R.string.france, R.drawable.flag_fr),
        ITALY(1, R.string.italy, R.drawable.flag_it),
        SPAIN(2, R.string.spain, R.drawable.flag_sp),
        GERMANY(3, R.string.germany, R.drawable.flag_ge),
        UKRAINE(4, R.string.ukraine, R.drawable.flag_ukr);

        public int id;
        public int title;
        public int picture;

        Country(int id, int title, int picture) {
            this.id = id;
            this.title = title;
            this.picture = picture;
        }
    }
}
