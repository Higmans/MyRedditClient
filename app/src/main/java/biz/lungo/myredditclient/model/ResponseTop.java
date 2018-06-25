
package biz.lungo.myredditclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseTop {

    @SerializedName("data")
    @Expose
    public Data data;

}
