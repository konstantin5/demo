package net.pilin.tinkoffnews.core

import retrofit2.http.GET
import io.reactivex.Observable
import net.pilin.tinkoffnews.core.entities.NewsContentResponse
import net.pilin.tinkoffnews.core.entities.NewsListResponse
import retrofit2.http.Query


interface TinkoffNewsApi {
    @GET("v1/news")
    fun getNewsList(): Observable<NewsListResponse>

    @GET("/v1/news_content")
    fun getNewsContent(@Query("id") payloadId: String): Observable<NewsContentResponse>
}