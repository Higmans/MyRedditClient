package biz.lungo.myredditclient.screens.topposts

import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.views.ObservableViewMvc

interface TopPostsListViewMvc: ObservableViewMvc<TopPostsListViewMvc.Listener> {

    interface Listener {
        fun onPostClicked(post: Post)
        fun onRefreshInitiated()
        fun onLoadMore()
    }
    fun bindPosts(posts: List<Post>)
    fun addPosts(posts: List<Post>)
    fun showLoadingIndicator(show: Boolean)
    fun showError(errorMessage: String)
    fun getPostsCount(): Int
    fun showLoadMoreIndicator(show: Boolean)

}