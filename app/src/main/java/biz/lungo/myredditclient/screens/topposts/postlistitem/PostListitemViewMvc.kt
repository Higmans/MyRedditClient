package biz.lungo.myredditclient.screens.topposts.postlistitem

import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.views.ObservableViewMvc

interface PostListItemViewMvc: ObservableViewMvc<PostListItemViewMvc.Listener> {

    interface Listener {
        fun onPostClicked(post: Post)
    }

    fun bindPost(post: Post)
}