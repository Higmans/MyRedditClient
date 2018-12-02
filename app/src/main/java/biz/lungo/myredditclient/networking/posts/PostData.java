
package biz.lungo.myredditclient.networking.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import biz.lungo.myredditclient.common.Utils;

public class PostData {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("post_hint")
    @Expose
    public String postHint;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("num_comments")
    @Expose
    public Integer numComments;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("created_utc")
    @Expose
    public Double createdUtc;

    /**
     * Check if post contains a thumbnail
     */
    public boolean hasThumbnail() {
        return Utils.isUrl(thumbnail);
    }
}
