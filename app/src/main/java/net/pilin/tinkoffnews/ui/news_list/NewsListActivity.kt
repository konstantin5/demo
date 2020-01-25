package net.pilin.tinkoffnews.ui.news_list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import net.pilin.tinkoffnews.ui.news_content.NewsExpandedActivity
import net.pilin.tinkoffnews.ui.news_content.NewsExpandedPresenter
import net.pilin.tinkoffnews.R

class NewsListActivity : MvpAppCompatActivity(), NewsListView {
    @InjectPresenter
    lateinit var presenter: NewsListPresenter

    @ProvidePresenter
    fun providePresenter(): NewsListPresenter {
        return NewsListPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList.layoutManager = LinearLayoutManager(this)

        swipe_refresh.setOnRefreshListener {
            presenter.onRefreshRequested()
        }
    }

    override fun setAdapter(adapter: NewsListAdapter) {
        newsList.adapter = adapter
    }

    override fun showProgress() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_refresh.isRefreshing = false
    }

    override fun showNetworkError() {
        val snackbar = Snackbar.make(
            root_view,
            R.string.error_unable_to_receive_news_content,
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(R.string.snackbar_retry) {
            presenter.onRefreshRequested()
            snackbar.dismiss()
        }
        snackbar.show()
    }

    override fun showNewsContentUnavailable() {
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.error_news_unavailable_title)
            .setMessage(R.string.error_news_unavailable_body)
            .create()
        dialog.show()
    }

    override fun navigateToItem(newsEntityId: String) {
        val intent = Intent(this, NewsExpandedActivity::class.java)
        intent.putExtra(NewsExpandedPresenter.NEWS_PARAM, newsEntityId)
        startActivity(intent)
    }
}
