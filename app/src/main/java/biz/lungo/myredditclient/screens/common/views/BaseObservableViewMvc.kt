package biz.lungo.myredditclient.screens.common.views

import java.util.*

open class BaseObservableViewMvc<ListenerType>: BaseViewMvc(), ObservableViewMvc<ListenerType> {

    private val mListeners = HashSet<ListenerType>()

    override fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }

    protected fun getListeners(): Set<ListenerType> {
        return Collections.unmodifiableSet(mListeners)
    }
}