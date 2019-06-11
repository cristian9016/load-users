package co.com.ceiba.mobile.pruebadeingreso.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Post(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "user_id") val userId: Int,
        val title: String,
        val body: String
)