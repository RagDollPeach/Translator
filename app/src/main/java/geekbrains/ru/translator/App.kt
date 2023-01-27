package geekbrains.ru.translator

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import geekbrains.ru.translator.model.data.AppState
import geekbrains.ru.translator.presenter.MainPresenterImpl
import geekbrains.ru.translator.presenter.Presenter
import geekbrains.ru.translator.utils.BASE_URL_LOCATIONS
import geekbrains.ru.translator.view.base.View
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplication = this
        presenter = createPresenter()
    }

    private fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl()
    }

    companion object {

        lateinit var presenter: Presenter<AppState, View>

        private var myApplication: App? = null
        fun getMyApp() = myApplication!!

    }

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