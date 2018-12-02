package biz.lungo.myredditclient.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import biz.lungo.myredditclient.screens.common.helpers.DialogsHelper
import biz.lungo.myredditclient.screens.topposts.TopPostsListViewMvc
import biz.lungo.myredditclient.screens.topposts.TopPostsListViewMvcImpl
import biz.lungo.myredditclient.screens.topposts.postlistitem.PostListItemViewMvc
import biz.lungo.myredditclient.screens.topposts.postlistitem.PostListItemViewMvcImpl

class ViewMvcFactory(
        private val mLayoutInflater: LayoutInflater,
        private val mDialogsHelper: DialogsHelper
) {

    fun getTopPostListViewMvc(parent: ViewGroup?): TopPostsListViewMvc {
        return TopPostsListViewMvcImpl(mLayoutInflater, parent, this, mDialogsHelper)
    }

    fun getPostListItemViewMvc(parent: ViewGroup, viewType: Int): PostListItemViewMvc {
        return PostListItemViewMvcImpl(mLayoutInflater, parent, viewType)
    }
}