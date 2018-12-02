package biz.lungo.myredditclient.screens.common.helpers

import biz.lungo.myredditclient.common.Constants.VIEW_TYPE_DEFAULT
import biz.lungo.myredditclient.common.Constants.VIEW_TYPE_IMAGE
import biz.lungo.myredditclient.networking.posts.Post

class Utils {

    companion object {
        fun getItemViewType(post: Post): Int {
            return when(post.data.postHint) {
                "image" -> VIEW_TYPE_IMAGE
                else -> VIEW_TYPE_DEFAULT
            }
        }
    }

}