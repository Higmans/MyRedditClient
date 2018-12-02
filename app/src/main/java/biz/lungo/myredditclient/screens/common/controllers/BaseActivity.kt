package biz.lungo.myredditclient.screens.common.controllers

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity

import biz.lungo.myredditclient.common.MRCApplication
import biz.lungo.myredditclient.common.di.ContextCompositionRoot

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    private lateinit var mContextCompositionRoot: ContextCompositionRoot

    fun getCompositionRoot(): ContextCompositionRoot {
        if (!::mContextCompositionRoot.isInitialized) {
            mContextCompositionRoot = ContextCompositionRoot(
                    (application as MRCApplication).compositionRoot,
                    this)
        }
        return mContextCompositionRoot
    }
}
