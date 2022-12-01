package geekbrains.ru.translator.view.base

import androidx.appcompat.app.AppCompatActivity
import geekbrains.ru.translator.model.data.AppState
import geekbrains.ru.translator.App

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    abstract override fun renderData(appState: AppState)

    override fun onStart() {
        super.onStart()
        App.presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        App.presenter.detachView(this)
    }
}
