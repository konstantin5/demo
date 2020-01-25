package net.pilin.tinkoffnews.ui.news_content

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_news_expanded.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import net.pilin.tinkoffnews.R
import net.pilin.tinkoffnews.show

class NewsExpandedActivity : MvpAppCompatActivity(),
    NewsExpandedView {

    @InjectPresenter
    lateinit var presenter: NewsExpandedPresenter

    @ProvidePresenter
    fun providePresenter(): NewsExpandedPresenter {
        return NewsExpandedPresenter(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_expanded)
        content.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun showProgress() {
        progress_bar.show(true)
        content.show(false)
    }

    override fun hideProgress() {
        progress_bar.show(false)
        content.show(true)
    }


    override fun showNewsContent(newsContent: String) {
        content.text = HtmlCompat.fromHtml(newsContent, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    override fun showGetNewsContentError() {
        val snackbar = Snackbar.make(
            root_view,
            R.string.error_unable_to_receive_news_content,
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(R.string.snackbar_retry) {
            presenter.onRetryGetNews()
            snackbar.dismiss()
        }
        snackbar.show()
    }
}

