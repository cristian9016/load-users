package co.com.ceiba.mobile.pruebadeingreso.data.rest

import co.com.ceiba.mobile.pruebadeingreso.data.models.Post
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.GET_POST_USER
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET(GET_POST_USER)
    fun getPost(@Query("userId") idUser: Int): Observable<List<Post>>

}