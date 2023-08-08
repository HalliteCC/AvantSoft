package com.example.avantsoft.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.avantsoft.data.repository.UserRepository
import com.example.avantsoft.domain.model.UserDataBaseModel


class UiViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)

    private val _users = MediatorLiveData<List<UserDataBaseModel>>()
    val users: LiveData<List<UserDataBaseModel>> = _users

    init {
        _users.addSource(userRepository.getUsersLiveData()) { users ->
            _users.value = users
        }
    }

    fun getUsersFromDatabase() {
        userRepository.getUsersFromDatabase()
    }

    fun delete(id: Int) {
        userRepository.delete(id)
    }
}



