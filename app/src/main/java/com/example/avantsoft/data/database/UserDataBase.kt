package com.example.avantsoft.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.avantsoft.data.dao.UsersDao
import com.example.avantsoft.domain.model.UserDataBaseModel



@Database(entities = [UserDataBaseModel::class], version = 1)
abstract class UserDataBase : RoomDatabase() {


    abstract fun userDAO(): UsersDao


    //Singleton
    companion object {
        private lateinit var INSTANCE: UserDataBase

        fun getDataBase(context: Context): UserDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(UserDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, UserDataBase::class.java, "userdb")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM User")
            }
        }
    }
}
