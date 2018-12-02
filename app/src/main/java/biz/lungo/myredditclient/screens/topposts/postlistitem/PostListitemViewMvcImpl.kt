package biz.lungo.myredditclient.screens.topposts.postlistitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import biz.lungo.myredditclient.R
import biz.lungo.myredditclient.common.Constants.*
import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.views.BaseObservableViewMvc
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_metadata.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class PostListItemViewMvcImpl(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        private val viewType: Int
): BaseObservableViewMvc<PostListItemViewMvc.Listener>(), PostListItemViewMvc {

    init {
        when (viewType) {
            VIEW_TYPE_LOADING -> setRootView(inflater.inflate(R.layout.item_post_loading, parent, false))
            VIEW_TYPE_IMAGE -> setRootView(inflater.inflate(R.layout.item_post_image, parent, false))
            VIEW_TYPE_DEFAULT -> setRootView(inflater.inflate(R.layout.item_post, parent, false))
            else -> setRootView(inflater.inflate(R.layout.item_post, parent, false))
        }
    }

    override fun bindPost(post: Post) {
        with(getRootView()) {
            if (viewType == VIEW_TYPE_LOADING) return // Do nothing for loading view placeholder

            if (post.data.hasThumbnail()) {
                iv_thumb.visibility = View.VISIBLE
                when (viewType) {
                    VIEW_TYPE_IMAGE -> {
                        Picasso.get().load(post.data.url).into(iv_thumb)
                        iv_thumb.setOnClickListener { getListeners().forEach { listener -> listener.onPostClicked(post) } }
                    }
                    else -> Picasso.get().load(post.data.thumbnail).fit().into(iv_thumb)
                }
            } else {
                iv_thumb.visibility = View.GONE
            }
            tv_title.text = post.data.title
            tv_author.text = post.data.author
            tv_comments.text = post.data.numComments.toString()
            tv_post_time.text = PrettyTime().format(Date(post.data.createdUtc.toLong() * 1000))
        }
    }
}