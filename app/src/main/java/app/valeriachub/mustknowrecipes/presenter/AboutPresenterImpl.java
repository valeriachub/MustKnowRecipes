package app.valeriachub.mustknowrecipes.presenter;

import android.content.Context;
import android.content.pm.PackageManager;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.model.About;
import app.valeriachub.mustknowrecipes.view.fragment.AboutView;

public class AboutPresenterImpl implements AboutPresenter {

    private Context context;
    private AboutView aboutView;

    public AboutPresenterImpl(Context context, AboutView aboutView) {
        this.context = context;
        this.aboutView = aboutView;
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case R.id.image_toolbar_nav: {
                aboutView.showDrawer();
                break;
            }
        }
    }

    @Override
    public void getAboutInfo() {
        String info = "Самые известные рецепты со вего мира. Те рецепты, которые представляют каждую страну " +
                "по своему. Те рецепты, которые открывают любые двери в мир кулинарии. Те рецепты, которые " +
                "должен знать каждый уважаемый себя шеф.";
        String version = "";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        About about = new About(info, context.getString(R.string.version) + " " + version);
        aboutView.showAboutInformation(about);
    }
}
