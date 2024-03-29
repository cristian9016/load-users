package co.com.ceiba.mobile.pruebadeingreso.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class User(
        @PrimaryKey val id: Int,
        val name: String,
        val email: String,
        val phone: String):Parcelable