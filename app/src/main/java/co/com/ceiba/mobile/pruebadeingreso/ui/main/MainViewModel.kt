package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.arch.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.data.db.AppDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.rest.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UsersService
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository
import co.com.ceiba.mobile.pruebadeingreso.util.applySchedulers

class MainViewModel : ViewModel() {

    private val userDao: UserDao = AppDatabase.db.userDao()
    private val userService: UsersService = RetrofitApi.getUserApi()
    private val repository = UserRepository(userDao, userService)

    fun getAllOffline() = repository.getAllOffline()
            .applySchedulers()

    fun getAllOnline() = repository.getAllOnline()
            .applySchedulers()

    fun searchUser(query: String) = repository.searchUser(query)
            .applySchedulers()


}