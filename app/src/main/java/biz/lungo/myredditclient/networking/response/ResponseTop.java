
package biz.lungo.myredditclient.networking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import biz.lungo.myredditclient.networking.posts.Data;

public class ResponseTop {

    @SerializedName("data")
    @Expose
    public Data data;

}
