package net.pilin.tinkoffnews.ui.news_list

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface NewsListView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun showNetworkError()
    fun setAdapter(adapter: NewsListAdapter)
    fun navigateToItem(newsEntityId: String)
    fun showNewsContentUnavailable()
}
