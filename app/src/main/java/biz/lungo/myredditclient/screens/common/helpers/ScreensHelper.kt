package biz.lungo.myredditclient.screens.common.helpers

import android.content.Intent
import android.support.v4.app.FragmentActivity
import biz.lungo.myredditclient.networking.posts.Post
import biz.lungo.myredditclient.screens.common.fragmenthelper.FragmentHelper
import biz.lungo.myredditclient.screens.topposts.TopPostsFragment
import com.himangi.imagepreview.ImagePreviewActivity

class ScreensHelper(private val mFragmentHelper: FragmentHelper, private val mActivity: FragmentActivity) {

    fun toTopPostsList() {
        mFragmentHelper.replaceFragment(TopPostsFragment.newInstance())
    }

    fun toImagePreviewScreen(post: Post) {
        val intent = Intent(mActivity, ImagePreviewActivity::class.java)
        intent.putExtra(ImagePreviewActivity.IMAGE_LIST, arrayListOf<String>(post.data.url))
        mActivity.startActivity(intent)
    }
}