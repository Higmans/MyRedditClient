package biz.lungo.myredditclient.common.di;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import org.jetbrains.annotations.NotNull;

import biz.lungo.myredditclient.screens.common.ViewMvcFactory;
import biz.lungo.myredditclient.screens.common.fragmenthelper.FragmentHelper;
import biz.lungo.myredditclient.screens.common.fragmenthelper.FragmentWrapper;
import biz.lungo.myredditclient.screens.common.helpers.DialogsHelper;
import biz.lungo.myredditclient.screens.common.helpers.ScreensHelper;
import biz.lungo.myredditclient.screens.topposts.TopPostsController;
import biz.lungo.myredditclient.topposts.FetchTopPostsUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ContextCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity mActivity;


    public ContextCompositionRoot(CompositionRoot compositionRoot, FragmentActivity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    @NotNull
    public ScreensHelper getScreensHelper() {
        return new ScreensHelper(getFragmentHelper(), getActivity());
    }

    private FragmentHelper getFragmentHelper() {
        return new FragmentHelper(getFragmentWrapper(), getFragmentManager());
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private FragmentWrapper getFragmentWrapper() {
        return (FragmentWrapper) getActivity();
    }

    private FragmentActivity getActivity() {
        return mActivity;
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getDialogsHelper());
    }

    private LayoutInflater getLayoutInflater() {
        return getActivity().getLayoutInflater();
    }

    private FetchTopPostsUseCase getFetchTopPostsUseCase() {
        return mCompositionRoot.getFetchTopPostsUseCase(AndroidSchedulers.mainThread());
    }

    @NotNull
    public TopPostsController getTopPostsController() {
        return new TopPostsController(getFetchTopPostsUseCase(), getScreensHelper());
    }

    private DialogsHelper getDialogsHelper() {
        return new DialogsHelper(getActivity());
    }
}
