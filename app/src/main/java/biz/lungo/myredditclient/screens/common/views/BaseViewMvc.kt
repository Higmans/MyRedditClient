package biz.lungo.myredditclient.screens.common.views

import android.content.Context
import android.support.annotation.StringRes
import android.view.View

open class BaseViewMvc: ViewMvc {

    private lateinit var mRootView: View

    override fun getRootView(): View = mRootView

    fun setRootView(view: View) {
        mRootView = view
    }

    private fun getContext(): Context {
        return getRootView().context
    }

    protected fun getString(@StringRes id: Int): String {
        return getContext().getString(id)
    }
}