package com.bignerdranch.android.trainingapp.ui.fragments.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.trainingapp.dagger.useCases.GetUserUseCase
import com.bignerdranch.android.trainingapp.dagger.useCases.GetUsersUseCase
import com.bignerdranch.android.trainingapp.data.remote.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadUsersViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(listOf())
    val users: StateFlow<List<User>>
        get() = _users.asStateFlow()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?>
        get() = _user.asStateFlow()

    private val exceptionsHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun getUsers() {
        viewModelScope.launch(exceptionsHandler) {
            getUsersUseCase().collect { items ->
                _users.emit(items)
            }
        }
    }

    fun getUser(id: Int) {
        viewModelScope.launch {
            getUserUseCase(id).collect { item ->
                _user.emit(item)
            }
        }
    }
}