package biz.lungo.myredditclient.networking;

import retrofit2.Retrofit;

public class ApiFactory {

    private Retrofit mRetrofit;

    public ApiFactory(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public RedditApi getRedditApi() {
        return mRetrofit.create(RedditApi.class);
    }
}
