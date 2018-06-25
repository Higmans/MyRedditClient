package biz.lungo.myredditclient.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import biz.lungo.myredditclient.R
import biz.lungo.myredditclient.app.MRCApplication
import biz.lungo.myredditclient.model.Post
import biz.lungo.myredditclient.view.adapter.PostsAdapter
import biz.lungo.myredditclient.view.helper.BottomNavigationViewBehavior
import biz.lungo.myredditclient.view.helper.MarginDecoration
import biz.lungo.myredditclient.viewModel.ListingViewModel
import com.himangi.imagepreview.ImagePreviewActivity
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*


class MainFragment : Fragment() {

    private lateinit var mViewModel: ListingViewModel
    private val itemsList = ArrayList<Post>()

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
        setupViews()
        val adapter = PostsAdapter(itemsList) { onImageClick(it) }
        rv_posts.adapter = adapter
        mViewModel.data.observeForever {
            itemsList.clear()
            for (item in it!!) {
                itemsList.add(item)
            }
            adapter.notifyDataSetChanged()
            rv_posts?.layoutManager?.scrollToPosition(0)
        }
    }

    private fun onImageClick(item: Post) {
        val intent = Intent(context, ImagePreviewActivity::class.java)
        intent.putExtra(ImagePreviewActivity.IMAGE_LIST, arrayListOf(item.data.url))
        startActivity(intent)
    }

    private fun setupViews() {
        (bottom_nav.layoutParams as CoordinatorLayout.LayoutParams).behavior = BottomNavigationViewBehavior()
        rv_posts.setHasFixedSize(true)
        rv_posts.addItemDecoration(MarginDecoration(MRCApplication.getInstance()))
        btn_prev.setOnClickListener { mViewModel.loadPrevious() }
        btn_next.setOnClickListener { mViewModel.loadNext() }
    }
}