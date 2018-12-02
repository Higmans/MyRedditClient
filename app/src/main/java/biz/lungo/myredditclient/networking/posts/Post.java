
package biz.lungo.myredditclient.networking.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("data")
    @Expose
    public PostData data;

}
