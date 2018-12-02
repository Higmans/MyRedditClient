package biz.lungo.myredditclient.screens.topposts

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import biz.lungo.myredditclient.common.Constants.VIEW_TYPE_LOADING
import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.ViewMvcFactory
import biz.lungo.myredditclient.screens.common.helpers.Utils
import biz.lungo.myredditclient.screens.topposts.postlistitem.PostListItemViewMvc

class PostsAdapter(
        private val mListener: Listener,
        private val mViewMvcFactory: ViewMvcFactory
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>(), PostListItemViewMvc.Listener {

    interface Listener {
        fun onPostClicked(post: Post)
        fun onLoadMore()
    }

    private var mPosts = ArrayList<Post>()
    private var mIsLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = mViewMvcFactory.getPostListItemViewMvc(parent, viewType)
        viewMvc.registerListener(this)
        return ViewHolder(viewMvc)
    }

    override fun onPostClicked(post: Post) {
        mListener.onPostClicked(post)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.mViewMvc.bindPost(mPosts[position])

    override fun getItemCount(): Int = mPosts.size

    private fun getLastItemPosition() = itemCount - 1

    override fun getItemViewType(position: Int): Int {
        return if (isLoading() && position == getLastItemPosition()) {
            VIEW_TYPE_LOADING
        } else {
            Utils.getItemViewType(mPosts[position])
        }
    }

    fun bindPosts(posts: List<Post>) {
        mPosts = ArrayList(posts)
        notifyDataSetChanged()
    }

    fun addPosts(posts: List<Post>) {
        val insertedPosition = getLastItemPosition()
        mPosts.addAll(posts)
        notifyItemInserted(insertedPosition)
    }

    fun setLoadingMore(isLoading: Boolean) {
        mIsLoading = isLoading
    }

    fun isLoading(): Boolean = mIsLoading

    class ViewHolder(val mViewMvc: PostListItemViewMvc) : RecyclerView.ViewHolder(mViewMvc.getRootView())
}