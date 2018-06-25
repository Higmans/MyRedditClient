package biz.lungo.myredditclient.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import biz.lungo.myredditclient.R
import biz.lungo.myredditclient.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_metadata.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

private const val VIEW_TYPE_DEFAULT = 0
private const val VIEW_TYPE_IMAGE = 1

class PostsAdapter(private val items: List<Post>, private val listener: (Post) -> Unit) : RecyclerView.Adapter<PostsAdapter.PostsAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapterViewHolder {
        return when(viewType) {
            VIEW_TYPE_IMAGE -> PostsAdapter.PostsAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_image, parent, false))
            VIEW_TYPE_DEFAULT -> PostsAdapter.PostsAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
            else -> PostsAdapter.PostsAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
        }
    }

    override fun onBindViewHolder(holder: PostsAdapterViewHolder, position: Int) = holder.bind(items[position], getItemViewType(position), listener)

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position].data.postHint) {
            "image" -> VIEW_TYPE_IMAGE
            else -> VIEW_TYPE_DEFAULT
        }
    }

    class PostsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post, viewType: Int, listener: (Post) -> Unit) = with(itemView) {
            if (post.data.hasThumbnail()) {
                itemView.iv_thumb.visibility = View.VISIBLE
                when (viewType) {
                    VIEW_TYPE_IMAGE -> {
                        Picasso.get().load(post.data.url).into(itemView.iv_thumb)
                        itemView.iv_thumb.setOnClickListener { listener(post) }
                    }
                    else -> Picasso.get().load(post.data.thumbnail).fit().into(itemView.iv_thumb)
                }
            } else {
                itemView.iv_thumb.visibility = View.GONE
            }
            itemView.tv_title.text = post.data.title
            itemView.tv_author.text = post.data.author
            itemView.tv_comments.text = post.data.numComments.toString()
            itemView.tv_post_time.text = PrettyTime().format(Date(post.data.createdUtc.toLong() * 1000))
        }
    }
}