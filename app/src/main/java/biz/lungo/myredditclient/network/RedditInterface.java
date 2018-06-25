package biz.lungo.myredditclient.network;

import biz.lungo.myredditclient.model.ResponseTop;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditInterface {

    @GET("/top.json")
    Call<ResponseTop> fetchTopPosts(@Query("limit") int limit,
                                    @Query("before") String before,
                                    @Query("after") String after,
                                    @Query("count") int count);
}
