package geekbrains.ru.translator.model.datasource

import geekbrains.ru.translator.model.data.DataModel
import geekbrains.ru.translator.App
import io.reactivex.Observable
import okhttp3.Interceptor

class RetrofitImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return getService(BaseInterceptor.interceptor).search(word)
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return App.getMyApp().createRetrofit(interceptor).create(ApiService::class.java)
    }
}
