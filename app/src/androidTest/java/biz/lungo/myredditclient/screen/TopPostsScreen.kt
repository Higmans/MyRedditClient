package biz.lungo.myredditclient.screen

import android.view.View
import biz.lungo.myredditclient.R
import com.agoda.kakao.*
import org.hamcrest.Matcher

class TopPostsScreen: Screen<TopPostsScreen>()  {
    val topPostsList: KRecyclerView = KRecyclerView({ withId(R.id.rv_posts)},
            itemTypeBuilder = { itemType(TopPostsScreen::Item) })
    val btnPrev = KView{ withId(R.id.btn_prev) }
    val btnNext = KView{ withId(R.id.btn_next) }

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.tv_title) }
        val thumb: KTextView = KTextView(parent) { withId(R.id.iv_thumb) }
    }
}