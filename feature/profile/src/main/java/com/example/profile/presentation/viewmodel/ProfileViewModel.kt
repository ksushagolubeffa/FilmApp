package com.example.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.domain.DeleteUserUseCase
import com.example.profile.domain.UpdateUserUseCase
import com.example.profile.domain.UserByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel constructor(
    private val userByIdUseCase: UserByIdUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {

    private var _state: MutableStateFlow<ProfileScreenState> = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    private fun getUserById(id: Int){
        viewModelScope.launch {
            try{
                userByIdUseCase(id).also{
                        userInfo -> _state.emit(_state.value.copy(
                    userInfo = userInfo
                ))
                }
            }catch(error: Throwable){
                _state.emit(_state.value.copy(
                    error = error
                ))
            }
        }
    }

    private fun updateUser(id: Int, email: String, username: String, password: ByteArray, points: Int){
        viewModelScope.launch {
            try{
                updateUserUseCase(id, email, username, password, points)
            }catch(error: Throwable){
                _state.emit(_state.value.copy(
                    error = error
                ))
            }
        }
    }

    private fun deleteUser(id: Int){
        viewModelScope.launch {
            try{
                deleteUserUseCase(id)
            }catch(error: Throwable){
                _state.emit(_state.value.copy(
                    error = error
                ))
            }
        }
    }

    fun reducer(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.OnDeleteUser -> deleteUser(event.id)
            is ProfileScreenEvent.OnLoadUserInfo -> getUserById(event.id)
            is ProfileScreenEvent.OnUpdateUser -> updateUser(event.id, event.email, event.username, event.password, event.points)
        }
    }

}