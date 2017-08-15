package app.valeriachub.mustknowrecipes.presenter;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.view.activity.MainView;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onItemClickListener(int id) {
        switch (id) {
            case R.id.drawer_layout: {
                mainView.showDrawer();
                break;
            }
        }
    }

    @Override
    public void isDrawerBlocked(boolean isBlocked) {
        if (isBlocked) {
            mainView.blockDrawer();
        } else {
            mainView.unblockDrawer();
        }
    }
}
