package co.com.ceiba.mobile.pruebadeingreso.data.rest

import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import co.com.ceiba.mobile.pruebadeingreso.data.rest.Endpoints.GET_USERS
import io.reactivex.Observable
import retrofit2.http.GET

interface UsersService {

    @GET(GET_USERS)
    fun getUsers(): Observable<List<User>>

}