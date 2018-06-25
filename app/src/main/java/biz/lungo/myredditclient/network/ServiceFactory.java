package biz.lungo.myredditclient.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static RedditInterface create(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RedditInterface.class);
    }

}
