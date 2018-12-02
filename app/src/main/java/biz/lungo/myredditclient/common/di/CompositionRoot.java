package biz.lungo.myredditclient.common.di;

import android.support.annotation.NonNull;

import biz.lungo.myredditclient.common.Constants;
import biz.lungo.myredditclient.networking.ApiFactory;
import biz.lungo.myredditclient.networking.RedditApi;
import biz.lungo.myredditclient.topposts.FetchTopPostsUseCase;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit mRetrofit;

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.REDDIT_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    @NonNull
    private ApiFactory getApiFactory() {
        return new ApiFactory(getRetrofit());
    }

    private RedditApi getRedditApi() {
        return getApiFactory().getRedditApi();
    }

    public FetchTopPostsUseCase getFetchTopPostsUseCase(Scheduler scheduler) {
        return new FetchTopPostsUseCase(getRedditApi(), scheduler);
    }
}
