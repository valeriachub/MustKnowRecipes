package app.valeriachub.mustknowrecipes.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.presenter.MainPresenter;
import app.valeriachub.mustknowrecipes.presenter.MainPresenterImpl;
import app.valeriachub.mustknowrecipes.utils.FragmentUtils;
import app.valeriachub.mustknowrecipes.utils.KeyboardUtils;
import app.valeriachub.mustknowrecipes.view.fragment.AboutFragment;
import app.valeriachub.mustknowrecipes.view.fragment.CuisinesFragment;
import app.valeriachub.mustknowrecipes.view.fragment.FavouritesFragment;
import app.valeriachub.mustknowrecipes.view.fragment.SearchFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.layout_container)
    FrameLayout containerView;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private MainPresenter mainPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        unbinder = ButterKnife.bind(this);
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initUI() {
        mainPresenter = new MainPresenterImpl(this);
        FragmentUtils.replace(getSupportFragmentManager(), R.id.layout_container, CuisinesFragment.getInstance(), "", false);
        setNavigationView();
    }

    private void setNavigationView() {
        navigationView.setCheckedItem(R.id.menu_cuisines);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_DRAGGING) {
                    KeyboardUtils.hideKeyboard(MainActivity.this, navigationView);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_cuisines: {
                        FragmentUtils.replace(getSupportFragmentManager(), R.id.layout_container, CuisinesFragment.getInstance(), "", false);
                        navigationView.setCheckedItem(R.id.menu_cuisines);
                        break;
                    }
                    case R.id.menu_favourites: {
                        FragmentUtils.replace(getSupportFragmentManager(), R.id.layout_container, FavouritesFragment.getInstance(), "", false);
                        navigationView.setCheckedItem(R.id.menu_favourites);
                        break;
                    }

                    case R.id.menu_search: {
                        FragmentUtils.replace(getSupportFragmentManager(), R.id.layout_container, SearchFragment.getInstance(), "", false);
                        navigationView.setCheckedItem(R.id.menu_search);
                        break;
                    }

                    case R.id.menu_about: {
                        FragmentUtils.replace(getSupportFragmentManager(), R.id.layout_container, AboutFragment.getInstance(), "", false);
                        navigationView.setCheckedItem(R.id.menu_about);
                        break;
                    }
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void openDrawer() {
        mainPresenter.onItemClickListener(R.id.drawer_layout);
    }

    /**
     * Override View
     */

    @Override
    public void showDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void blockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unblockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}
