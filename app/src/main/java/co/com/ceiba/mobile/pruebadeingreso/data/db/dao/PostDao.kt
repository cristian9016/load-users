package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.models.Post
import io.reactivex.Flowable

@Dao
interface PostDao {

    @Query("SELECT * from post WHERE user_id = :id")
    fun getPost(id: Int): Flowable<List<Post>>

    @Insert
    fun insertAll(posts: List<Post>)
}