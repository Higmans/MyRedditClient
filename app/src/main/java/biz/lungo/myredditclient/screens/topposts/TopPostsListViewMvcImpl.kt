package biz.lungo.myredditclient.screens.topposts

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import biz.lungo.myredditclient.R
import biz.lungo.myredditclient.common.Constants.LOAD_MORE_THRESHOLD
import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.ViewMvcFactory
import biz.lungo.myredditclient.screens.common.helpers.DialogsHelper
import biz.lungo.myredditclient.screens.common.recyclerview.AutofitRecyclerView
import biz.lungo.myredditclient.screens.common.recyclerview.MarginDecoration
import biz.lungo.myredditclient.screens.common.views.BaseObservableViewMvc
import kotlinx.android.synthetic.main.fragment_top_posts.view.*

class TopPostsListViewMvcImpl(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        mViewMvcFactory: ViewMvcFactory,
        private val mDialogsHelper: DialogsHelper
): BaseObservableViewMvc<TopPostsListViewMvc.Listener>(), TopPostsListViewMvc, PostsAdapter.Listener {

    private val mTopPostsRecyclerView: AutofitRecyclerView
    private val mTopPostsAdapter: PostsAdapter
    private val mSwipeRefresh: SwipeRefreshLayout

    init {
        setRootView(inflater.inflate(R.layout.fragment_top_posts, parent, false))
        getRootView().rv_posts.setHasFixedSize(true)
        getRootView().rv_posts.addItemDecoration(MarginDecoration(getRootView().context))
        mTopPostsRecyclerView = getRootView().rv_posts
        mSwipeRefresh = getRootView().swipe_refresh
        mSwipeRefresh.isRefreshing = false
        mSwipeRefresh.setOnRefreshListener { getListeners().forEach { listener -> listener.onRefreshInitiated() } }
        mTopPostsAdapter = PostsAdapter(this, mViewMvcFactory)
        mTopPostsRecyclerView.adapter = mTopPostsAdapter
        setupLoadMoreListener()
    }

    private fun setupLoadMoreListener() {
        val layoutManager = mTopPostsRecyclerView.layoutManager as LinearLayoutManager
        mTopPostsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (!mTopPostsAdapter.isLoading() && totalItemCount <= (lastVisibleItem + LOAD_MORE_THRESHOLD)) {
                    mTopPostsAdapter.setLoadingMore(true)
                    onLoadMore()
                }
            }
        })
    }

    override fun onPostClicked(post: Post) {
        getListeners().forEach { listener -> listener.onPostClicked(post) }
    }

    override fun onLoadMore() {
        getListeners().forEach { listener -> listener.onLoadMore() }
    }

    override fun bindPosts(posts: List<Post>) {
        mTopPostsAdapter.bindPosts(posts)
    }

    override fun addPosts(posts: List<Post>) {
        mTopPostsAdapter.addPosts(posts)
    }

    override fun getPostsCount(): Int = mTopPostsAdapter.itemCount

    override fun showLoadingIndicator(show: Boolean) {
        mSwipeRefresh.isRefreshing = show
    }

    override fun showLoadMoreIndicator(show: Boolean) {
        mTopPostsAdapter.setLoadingMore(show)
    }

    override fun showError(errorMessage: String) {
        mDialogsHelper.showErrorDialog(getString(R.string.error_title), errorMessage)
    }

}