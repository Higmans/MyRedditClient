package biz.lungo.myredditclient.screens.common.fragmenthelper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentHelper(private val mFragmentFrameWrapper: FragmentWrapper, private val mFragmentManager: FragmentManager) {

    private val fragmentFrameId: Int
        get() = mFragmentFrameWrapper.getFragmentContainer().id

    fun replaceFragment(newFragment: Fragment) {
        val ft = mFragmentManager.beginTransaction()
        ft.replace(fragmentFrameId, newFragment, null)

        if (mFragmentManager.isStateSaved) {
            ft.commitAllowingStateLoss()
        } else {
            ft.commit()
        }
    }
}