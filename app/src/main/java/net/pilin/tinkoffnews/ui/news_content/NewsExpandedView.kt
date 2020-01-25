package net.pilin.tinkoffnews.ui.news_content

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface NewsExpandedView :MvpView{
    fun showProgress()
    fun showNewsContent(newsContent: String)
    fun hideProgress()
    fun showGetNewsContentError()
}
