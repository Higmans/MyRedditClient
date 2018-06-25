package biz.lungo.myredditclient

import android.support.test.rule.ActivityTestRule
import biz.lungo.myredditclient.screen.TopPostsScreen
import biz.lungo.myredditclient.view.MainActivity
import org.junit.Rule
import org.junit.Test

//TODO - use mocked data
class MainScreenTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)
    private val screen = TopPostsScreen()

    @Test
    fun testInitialLoading() {
        screen {
            idle(1000)
            topPostsList {
                firstChild<TopPostsScreen.Item> {
                    isVisible()
                    title { hasAnyText() }
                }
            }
        }
    }

    @Test
    fun testPagination() {
        screen {
            idle(1000)
            btnNext {
                isVisible()
                click()
            }
            idle(1000)
            topPostsList {
                firstChild<TopPostsScreen.Item> {
                    isVisible()
                    title { hasAnyText() }
                }
            }
            btnNext {
                click()
            }
            idle(1000)
            topPostsList {
                firstChild<TopPostsScreen.Item> {
                    isVisible()
                    title { hasAnyText() }
                }
            }
            btnPrev {
                click()
            }
            idle(1000)
        }
    }
}