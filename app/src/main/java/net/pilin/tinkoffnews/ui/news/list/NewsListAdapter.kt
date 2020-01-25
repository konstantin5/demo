package net.pilin.tinkoffnews.ui.news.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import net.pilin.tinkoffnews.R
import net.pilin.tinkoffnews.core.entities.NewsEntity

class NewsListAdapter(private val itemClick: (news: NewsEntity) -> Unit) :
    RecyclerView.Adapter<NewsListItemViewHolder>() {

    private val items = ArrayList<NewsEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListItemViewHolder {
        return NewsListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewsListItemViewHolder, position: Int) {
        val text = items[position].text
        if (text != null) {
            holder.newsName.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        holder.itemView.setOnClickListener { itemClick.invoke(items[position]) }
    }

    fun setItems(news: List<NewsEntity>) {
        items.clear()
        items.addAll(news)
    }

}

class NewsListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var newsName: TextView = view.findViewById(R.id.news_name)
}
