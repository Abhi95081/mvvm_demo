package com.example.mvvm_demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_demo.model.User
import com.example.mvvm_demo.model.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchUserData() {
        _isLoading.value = true
        viewModelScope.launch {
            val userResult = userRepository.fetchUser()
            _userData.postValue(userResult)
            _isLoading.postValue(false)
        }
    }
}
