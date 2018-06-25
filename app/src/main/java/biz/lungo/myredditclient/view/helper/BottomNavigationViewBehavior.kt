package biz.lungo.myredditclient.view.helper

import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.LinearLayout

class BottomNavigationViewBehavior : CoordinatorLayout.Behavior<LinearLayout>() {

    private var height: Int = 0

    override fun onLayoutChild(parent: CoordinatorLayout?, child: LinearLayout?, layoutDirection: Int): Boolean {
        height = child!!.height
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                            child: LinearLayout, directTargetChild: View, target: View,
                            axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: LinearLayout,
                       target: View, dxConsumed: Int, dyConsumed: Int,
                       dxUnconsumed: Int, dyUnconsumed: Int,
                       @ViewCompat.NestedScrollType type: Int) {
        if (dyConsumed > 0) {
            slideDown(child)
        } else if (dyConsumed < 0) {
            slideUp(child)
        }
    }

    private fun slideUp(child: LinearLayout) {
        child.clearAnimation()
        child.animate().translationY(0F).duration = 200
    }

    private fun slideDown(child: LinearLayout) {
        child.clearAnimation()
        child.animate().translationY(height.toFloat()).duration = 200
    }
}