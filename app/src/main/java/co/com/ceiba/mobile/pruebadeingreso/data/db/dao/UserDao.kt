package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<User>>

}