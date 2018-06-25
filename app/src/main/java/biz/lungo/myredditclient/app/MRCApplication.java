package biz.lungo.myredditclient.app;

import android.app.Application;
import android.content.SharedPreferences;

import org.jetbrains.annotations.Contract;

import biz.lungo.myredditclient.BuildConfig;
import biz.lungo.myredditclient.network.RedditInterface;
import biz.lungo.myredditclient.network.ServiceFactory;
import biz.lungo.myredditclient.util.Constants;

public class MRCApplication extends Application {

    private static MRCApplication instance;

    private RedditInterface redditInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Contract(pure = true)
    public static MRCApplication getInstance() {
        return instance;
    }

    public RedditInterface getRedditInterface() {
        if (redditInterface == null) {
            redditInterface = ServiceFactory.create(Constants.REDDIT_BASE_URL);
        }
        return redditInterface;
    }

    public SharedPreferences getPreferences() {
        return getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
    }
}