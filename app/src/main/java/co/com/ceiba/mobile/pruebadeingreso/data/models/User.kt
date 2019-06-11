package co.com.ceiba.mobile.pruebadeingreso.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class User(
        @PrimaryKey val id: Int,
        val name: String,
        val email: String,
        val phone: String)