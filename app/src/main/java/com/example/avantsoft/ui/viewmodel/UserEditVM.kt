package com.example.avantsoft.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.avantsoft.data.repository.UserRepository
import com.example.avantsoft.domain.model.UserDataBaseModel

class UserEditVM  (application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)

    private val _userSave = MutableLiveData<UserDataBaseModel>()
    var userSave: LiveData<UserDataBaseModel> = _userSave

    fun get(id: Int) {
        _userSave.value = userRepository.get(id)
    }


    fun update(user: UserDataBaseModel) {
        userRepository.update(user)
    }


}