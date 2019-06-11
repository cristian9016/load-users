package co.com.ceiba.mobile.pruebadeingreso.ui.post

import android.arch.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.data.db.AppDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.PostDao
import co.com.ceiba.mobile.pruebadeingreso.data.rest.PostService
import co.com.ceiba.mobile.pruebadeingreso.data.rest.RetrofitApi
import co.com.ceiba.mobile.pruebadeingreso.repository.PostRepository
import co.com.ceiba.mobile.pruebadeingreso.util.applySchedulers

class PostViewModel : ViewModel() {

    private val postDao: PostDao = AppDatabase.db.postDao()
    private val postService: PostService = RetrofitApi.getPostApi()
    private val repository = PostRepository(postDao, postService)

    fun getOfflinePost(id: Int) = repository.getOfflinePost(id)
            .applySchedulers()

    fun getOnlinePost(id: Int) = repository.getOnlinePost(id)
            .applySchedulers()
}