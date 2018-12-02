package biz.lungo.myredditclient.screens.topposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import biz.lungo.myredditclient.screens.common.controllers.BaseFragment


class TopPostsFragment : BaseFragment() {

    private lateinit var mViewMvc: TopPostsListViewMvc
    private lateinit var mTopPostsController: TopPostsController
    private var mViewStateRestored = false

    companion object {
        fun newInstance(): TopPostsFragment {
            return TopPostsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        retainInstance = true
        if (savedInstanceState == null) {
            mTopPostsController = getCompositionRoot().topPostsController
            mViewMvc = getCompositionRoot().viewMvcFactory.getTopPostListViewMvc(container)
            mTopPostsController.bindView(mViewMvc)
            mViewStateRestored = true
    }
        return mViewMvc.getRootView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        mViewStateRestored = mViewStateRestored || savedInstanceState != null
    }

    override fun onStart() {
        super.onStart()
        mTopPostsController.onStart(mViewStateRestored)
    }

    override fun onStop() {
        super.onStop()
        mTopPostsController.onStop()
    }
}