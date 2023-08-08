package com.example.avantsoft.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.avantsoft.data.database.UserDataBase
import com.example.avantsoft.domain.model.UserDataBaseModel

class UserRepository(context: Context) {

    private val uiRepository = UiRepository(context)
    private val userDataBase = UserDataBase.getDataBase(context).userDAO()


    // Adicione um LiveData para armazenar a lista de usuários
    private val usersLiveData: LiveData<List<UserDataBaseModel>> = userDataBase.getAllUsersLiveData()

    // Método para obter o LiveData
    fun getUsersLiveData(): LiveData<List<UserDataBaseModel>> {
        return usersLiveData
    }

    init {
        getUsersFromDatabase()
    }


    fun getUsersFromDatabase() {
        uiRepository.getUsersAndInsertIntoDatabase()
    }

    fun delete(id: Int) {
        val users = get(id)
        userDataBase.delete(users)
    }

    fun get(id: Int): UserDataBaseModel {
        return userDataBase.get(id)
    }

    fun update(users: UserDataBaseModel): Boolean {
        return userDataBase.update(users) > 0
    }
}