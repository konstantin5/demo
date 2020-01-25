package net.pilin.tinkoffnews.ui.news_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import moxy.InjectViewState
import net.pilin.tinkoffnews.ui.BasePresenter
import net.pilin.tinkoffnews.core.TinkoffNewsApi
import net.pilin.tinkoffnews.TinkoffNewsApp
import net.pilin.tinkoffnews.core.entities.NewsEntity
import javax.inject.Inject

@InjectViewState
class NewsListPresenter : BasePresenter<NewsListView>() {
    @Inject
    lateinit var api: TinkoffNewsApi

    private val adapter = NewsListAdapter { onNewsClicked(it) }

    init {
        TinkoffNewsApp.application.daggerAppComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getNewsList()
    }

    override fun attachView(view: NewsListView?) {
        super.attachView(view)
        viewState.setAdapter(adapter)
    }

    private fun getNewsList() {
        viewState.showProgress()
        val disposable = api.getNewsList()
            .subscribeOn(IoScheduler())
            .map { response ->
                response.payload =
                    response?.payload?.sortedByDescending { it.publicationDate?.milliseconds }
                response
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewState.hideProgress()
                if (response != null && response.resultCode == "OK") {
                    val payload = response.payload
                    if (payload != null) {
                        adapter.setItems(payload)
                        adapter.notifyDataSetChanged()
                    } else {
                        viewState.showNetworkError()
                    }
                } else {
                    viewState.showNetworkError()
                }

            }, {
                viewState.hideProgress()
                viewState.showNetworkError()
            })
        unsubscribe(disposable)
    }

    private fun onNewsClicked(newsEntity: NewsEntity) {
        val newsEntityId = newsEntity.id
        if (newsEntityId != null) {
            viewState.navigateToItem(newsEntityId)
        } else {
            viewState.showNewsContentUnavailable()
        }

    }

    fun onRefreshRequested() {
        getNewsList()
    }
}
