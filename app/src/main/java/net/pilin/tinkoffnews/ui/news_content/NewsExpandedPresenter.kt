package net.pilin.tinkoffnews.ui.news_content

import android.content.Intent
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import moxy.InjectViewState
import net.pilin.tinkoffnews.ui.BasePresenter
import net.pilin.tinkoffnews.core.TinkoffNewsApi
import net.pilin.tinkoffnews.TinkoffNewsApp
import java.lang.RuntimeException
import javax.inject.Inject

@InjectViewState
class NewsExpandedPresenter(intent: Intent) : BasePresenter<NewsExpandedView>() {

    @Inject
    lateinit var api: TinkoffNewsApi

    private var newsId: String

    init {
        TinkoffNewsApp.application.daggerAppComponent.inject(this)
        newsId =
            intent.getStringExtra(NEWS_PARAM) ?: throw RuntimeException("Invalid usage of activity")
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getNewsContent(newsId)
    }

    private fun getNewsContent(id: String) {
        viewState.showProgress()
        val disposable = api.getNewsContent(id)
            .subscribeOn(IoScheduler())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewState.hideProgress()
                if (response != null && response.resultCode == "OK") {
                    val content = response.payload?.content
                    if (content != null) {
                        viewState.showNewsContent(content)
                    } else {
                        viewState.showGetNewsContentError()
                    }
                }else{
                    viewState.showGetNewsContentError()
                }

            }, { error ->
                viewState.hideProgress()
                viewState.showGetNewsContentError()
            })
        unsubscribe(disposable)
    }

    fun onRetryGetNews() {
        getNewsContent(newsId)
    }

    companion object {
        const val NEWS_PARAM = "newsParam"
    }
}
