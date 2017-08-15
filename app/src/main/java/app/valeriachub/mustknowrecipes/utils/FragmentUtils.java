package app.valeriachub.mustknowrecipes.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtils {

    public static void replace(FragmentManager fragmentManager, int id, Fragment fragment, String tag , boolean isAddToBackStack){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(id, fragment, tag);
        if(isAddToBackStack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}
