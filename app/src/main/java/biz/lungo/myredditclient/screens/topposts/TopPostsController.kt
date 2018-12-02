package biz.lungo.myredditclient.screens.topposts

import biz.lungo.myredditclient.common.Constants
import biz.lungo.myredditclient.common.Constants.VIEW_TYPE_IMAGE
import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.helpers.ScreensHelper
import biz.lungo.myredditclient.screens.common.helpers.Utils.Companion.getItemViewType
import biz.lungo.myredditclient.topposts.FetchTopPostsUseCase

class TopPostsController(
        private val mFetchTopPostsUseCase: FetchTopPostsUseCase,
        private val mScreensHelper: ScreensHelper
): TopPostsListViewMvc.Listener, FetchTopPostsUseCase.Listener {

    private lateinit var mViewMvc: TopPostsListViewMvc

    fun bindView(viewMvc: TopPostsListViewMvc) {
        mViewMvc = viewMvc
        mViewMvc.registerListener(this)
    }

    fun onStart(viewStateRestored: Boolean) {
        mViewMvc.registerListener(this)
        mFetchTopPostsUseCase.registerListener(this)

        if (!viewStateRestored || mViewMvc.getPostsCount() == 0) {
            mViewMvc.showLoadingIndicator(true)
            mFetchTopPostsUseCase.loadTopPostsAndNotify()
        }
    }

    fun onStop() {
        mViewMvc.unregisterListener(this)
        mFetchTopPostsUseCase.unregisterListener(this)
    }

    override fun onPostClicked(post: Post) {
        if (getItemViewType(post) == VIEW_TYPE_IMAGE) {
            mScreensHelper.toImagePreviewScreen(post)
        } else {
            // it is not needed to implement posts opening at this moment
            return
        }
    }

    override fun onTopPostsFetched(posts: MutableList<Post>) {
        mViewMvc.showLoadingIndicator(false)
        mViewMvc.bindPosts(posts)
    }

    override fun onTopPostsMoreFetched(posts: MutableList<Post>) {
        mViewMvc.showLoadMoreIndicator(false)
        mViewMvc.addPosts(posts)
    }

    override fun onTopPostsFetchFailed(errorMessage: String) {
        mViewMvc.showError(errorMessage)
    }

    override fun onRefreshInitiated() {
        mFetchTopPostsUseCase.loadTopPostsAndNotify()
    }

    override fun onLoadMore() {
        if (mViewMvc.getPostsCount() < Constants.POSTS_SHOW_LIMIT) {
            mViewMvc.showLoadMoreIndicator(true)
            mFetchTopPostsUseCase.loadMoreTopPostsAndNotify()
        } else {
            mViewMvc.showLoadMoreIndicator(false)
        }
    }
}