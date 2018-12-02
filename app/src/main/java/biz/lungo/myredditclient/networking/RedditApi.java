package biz.lungo.myredditclient.networking;

import biz.lungo.myredditclient.networking.response.ResponseTop;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditApi {

    @GET("/top.json")
    Observable<ResponseTop> fetchTopPosts(@Query("limit") int limit,
                                          @Query("before") String before,
                                          @Query("after") String after);
}
