package biz.lungo.myredditclient.common;

import android.app.Application;

import biz.lungo.myredditclient.common.di.CompositionRoot;

public class MRCApplication extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}