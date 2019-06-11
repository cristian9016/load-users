package co.com.ceiba.mobile.pruebadeingreso.repository

import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.PostDao
import co.com.ceiba.mobile.pruebadeingreso.data.models.Post
import co.com.ceiba.mobile.pruebadeingreso.data.rest.PostService
import co.com.ceiba.mobile.pruebadeingreso.util.applySchedulers
import io.reactivex.Observable

class PostRepository(private val postDao: PostDao,private val postService: PostService) {

    fun getOfflinePost(id: Int) = postDao.getPost(id)
            .applySchedulers()

    fun getOnlinePost(id: Int) = postService.getPost(id)
            .map {
                insertDbRecords(it).subscribe()
                it
            }
            .applySchedulers()

    private fun insertDbRecords(post: List<Post>) =
            Observable.fromCallable {
                postDao.insertAll(post)
            }.applySchedulers()

}