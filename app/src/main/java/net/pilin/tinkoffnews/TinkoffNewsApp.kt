package net.pilin.tinkoffnews

import android.app.Application
import net.pilin.tinkoffnews.dagger.AppComponent
import net.pilin.tinkoffnews.dagger.DaggerAppComponent
import net.pilin.tinkoffnews.dagger.modules.ContextModule

class TinkoffNewsApp : Application() {

    val daggerAppComponent: AppComponent by lazy {
        DaggerAppComponent.builder().contextModule(
            ContextModule(
                this
            )
        ).build()
    }


    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: TinkoffNewsApp
    }
}