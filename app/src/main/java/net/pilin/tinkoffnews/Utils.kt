package net.pilin.tinkoffnews

import android.view.View

object RxUtils {
//    fun applySchedulers(): Transformer {
//
//    }


}


fun View.show(show: Boolean) {
    this.visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}