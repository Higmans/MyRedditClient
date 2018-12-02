package biz.lungo.myredditclient.screens.common.helpers

import android.content.Context
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

class DialogsHelper(private val mContext: Context) {

    fun showErrorDialog(title: String, message: String) {
        mContext.alert(message, title) {
            okButton { it.dismiss() }
        }.show()
    }

}