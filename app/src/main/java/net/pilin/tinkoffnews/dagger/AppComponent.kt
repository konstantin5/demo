package net.pilin.tinkoffnews.dagger

import dagger.Component
import net.pilin.tinkoffnews.dagger.modules.ContextModule
import net.pilin.tinkoffnews.dagger.modules.NetworkModule
import net.pilin.tinkoffnews.ui.news_content.NewsExpandedPresenter
import net.pilin.tinkoffnews.ui.news_list.NewsListPresenter

@Component(
    modules = [
        NetworkModule::class,
        ContextModule::class
    ]
)
interface AppComponent {
    fun inject(presenter: NewsListPresenter)
    fun inject(presenter: NewsExpandedPresenter)
}