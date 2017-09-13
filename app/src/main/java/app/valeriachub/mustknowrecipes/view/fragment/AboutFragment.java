package app.valeriachub.mustknowrecipes.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.valeriachub.mustknowrecipes.R;
import app.valeriachub.mustknowrecipes.data.model.About;
import app.valeriachub.mustknowrecipes.presenter.AboutPresenter;
import app.valeriachub.mustknowrecipes.presenter.AboutPresenterImpl;
import app.valeriachub.mustknowrecipes.view.activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutFragment extends Fragment implements AboutView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_toolbar_nav)
    ImageView toolbarNavView;
    @BindView(R.id.text_title)
    TextView titleView;
    @BindView(R.id.text_info)
    TextView infoView;
    @BindView(R.id.text_version)
    TextView versionView;

    private Unbinder unbinder;
    private MainActivity mainActivity;
    private AboutPresenter aboutPresenter;

    public static AboutFragment getInstance() {
        return new AboutFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainActivity = (MainActivity) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initUI() {
        aboutPresenter = new AboutPresenterImpl(getActivity(), this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarNavView.setImageResource(R.drawable.ic_more);
        titleView.setText(getString(R.string.about));
        aboutPresenter.getAboutInfo();
    }

    @OnClick(R.id.image_toolbar_nav)
    void onMoreClicked(View view) {
        aboutPresenter.onItemClicked(view.getId());
    }

    /**
     * Override View
     */

    @Override
    public void showDrawer() {
        mainActivity.openDrawer();
    }

    @Override
    public void showAboutInformation(About about) {
        infoView.setText(about.getInfo());
        versionView.setText(about.getVersion());
    }
}
