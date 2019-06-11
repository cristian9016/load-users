package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.arch.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.data.db.AppDatabase.Companion.db
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import co.com.ceiba.mobile.pruebadeingreso.data.rest.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UsersService
import co.com.ceiba.mobile.pruebadeingreso.util.applySchedulers
import io.reactivex.Observable

class MainViewModel : ViewModel() {
    private val userDao: UserDao = db.userDao()
    private val userService: UsersService = RetrofitApi.getUserApi()

    fun getAllOffline() = userDao.getAll()
            .applySchedulers()

    fun getAllOnline() = userService.getUsers()
            .map {
                insertDbRecords(it).subscribe()
                it
            }
            .applySchedulers()

    private fun insertDbRecords(users: List<User>) =
            Observable.fromCallable {
                userDao.insertAll(users)
            }.applySchedulers()

    fun searchUser(query:String) = userDao.findUser(query)
            .applySchedulers()


}