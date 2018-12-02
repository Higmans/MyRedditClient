package biz.lungo.myredditclient.screens.common.controllers

import android.support.v4.app.Fragment
import biz.lungo.myredditclient.common.MRCApplication
import biz.lungo.myredditclient.common.di.ContextCompositionRoot

open class BaseFragment: Fragment() {
    private lateinit var mContextCompositionRoot: ContextCompositionRoot

    fun getCompositionRoot(): ContextCompositionRoot {
        if (!::mContextCompositionRoot.isInitialized) {
            mContextCompositionRoot = ContextCompositionRoot(
                    (requireActivity().application as MRCApplication).compositionRoot,
                    requireActivity())
        }
        return mContextCompositionRoot
    }
}