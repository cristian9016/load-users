package co.com.ceiba.mobile.pruebadeingreso.view.main

import android.arch.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.data.db.AppDatabase.Companion.db
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.rest.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UsersService
import co.com.ceiba.mobile.pruebadeingreso.exts.applySchedulers

class MainViewModel : ViewModel() {
    private val userDao: UserDao = db.userDao()
    private val userService: UsersService = RetrofitApi.getUserApi()

    fun getAll() = userDao.getAll()
            .applySchedulers()

}