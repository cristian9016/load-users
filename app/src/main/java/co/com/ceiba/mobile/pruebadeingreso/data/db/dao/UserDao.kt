package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<User>>

    @Insert
    fun insertAll(users: List<User>)

    @Query("SELECT * from user WHERE name LIKE :query")
    fun findUser(query:String) : Flowable<List<User>>

}