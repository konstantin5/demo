package net.pilin.tinkoffnews.dagger.modules

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import net.pilin.tinkoffnews.BuildConfig
import net.pilin.tinkoffnews.core.TinkoffNewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import java.io.File


@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): TinkoffNewsApi {
        return retrofit.create(TinkoffNewsApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    internal fun provideHttpCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        val cacheSize = 10 * 1024 * 1024L
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Reusable
    internal fun provideHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(CacheInterceptor())
            .cache(cache)
            .build()
    }


    class CacheInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(15, TimeUnit.MINUTES) // 15 minutes cache
                .build()
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }
}