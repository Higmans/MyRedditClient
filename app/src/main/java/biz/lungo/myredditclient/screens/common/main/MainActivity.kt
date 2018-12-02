package biz.lungo.myredditclient.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import biz.lungo.myredditclient.R
import biz.lungo.myredditclient.screens.common.controllers.BaseActivity
import biz.lungo.myredditclient.screens.common.fragmenthelper.FragmentWrapper
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity(), FragmentWrapper {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val screensHelper = getCompositionRoot().screensHelper

        if (savedInstanceState == null) {
            screensHelper.toTopPostsList()
        }
    }

    override fun getFragmentContainer(): FrameLayout = container
}
