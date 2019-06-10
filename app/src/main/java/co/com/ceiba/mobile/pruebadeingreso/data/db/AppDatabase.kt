package co.com.ceiba.mobile.pruebadeingreso.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.models.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        lateinit var db: AppDatabase
        fun init(context: Context) {
            Room.databaseBuilder(context, AppDatabase::class.java, "ceiba-test")
                    .build()
        }
    }

}