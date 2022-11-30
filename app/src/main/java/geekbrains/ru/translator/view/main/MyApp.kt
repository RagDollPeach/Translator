package geekbrains.ru.translator.view.main

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }

    companion object {

        private var myApplication: MyApp? = null
        fun getMyApp() = myApplication!!

        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"

        fun createRetrofit(interceptor: Interceptor): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_LOCATIONS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient(interceptor))
                .build()
        }

        private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            return httpClient.build()
        }
    }
}