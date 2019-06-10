package co.com.ceiba.mobile.pruebadeingreso

import android.app.Application
import co.com.ceiba.mobile.pruebadeingreso.data.db.AppDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.rest.RetrofitApi

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitApi.init()
        AppDatabase.init(this)
    }

}