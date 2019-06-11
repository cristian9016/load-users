package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import co.com.ceiba.mobile.pruebadeingreso.data.rest.UsersService
import io.reactivex.Observable

class UserRepository(private val userDao: UserDao,private val userService: UsersService) {

    fun getAllOffline() = userDao.getAll()


    fun getAllOnline() = userService.getUsers()
            .map {
                insertDbRecords(it).subscribe()
                it
            }

    private fun insertDbRecords(users: List<User>) =
            Observable.fromCallable {
                userDao.insertAll(users)
            }

    fun searchUser(query: String) = userDao.findUser(query)

}