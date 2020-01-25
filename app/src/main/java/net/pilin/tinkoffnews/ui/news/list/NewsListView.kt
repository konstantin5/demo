package net.pilin.tinkoffnews.ui.news.list

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface NewsListView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun showNetworkError()
    fun setAdapter(adapter: NewsListAdapter)
    fun showNewsContentUnavailable()
    @StateStrategyType(SkipStrategy::class)
    fun navigateToItem(newsEntityId: String)
}
