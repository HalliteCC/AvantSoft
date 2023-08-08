package com.example.avantsoft.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.avantsoft.domain.model.UserDataBaseModel


@Dao
interface UsersDao {

    @Insert
    fun insert(user: UserDataBaseModel): Long

    @Update
    fun update (productsList: UserDataBaseModel): Int

    @Query("SELECT * FROM User WHERE id = :id")
    fun get(id: Int): UserDataBaseModel

    @Delete
    fun delete(user: UserDataBaseModel)

    @Query("SELECT * FROM User")
    fun getAllUser():List<UserDataBaseModel>

    @Query("SELECT * FROM User")
    fun getAllUsersLiveData(): LiveData<List<UserDataBaseModel>>

}