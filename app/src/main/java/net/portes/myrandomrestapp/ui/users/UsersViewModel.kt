package net.portes.myrandomrestapp.ui.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.models.UserDto
import net.portes.users.domain.usecases.GetUsersListUseCase
import net.portes.users.domain.usecases.SaveUserUseCase

class UsersViewModel @ViewModelInject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : BaseViewModel() {

    private val _usersList = MutableLiveData<ViewState<UserDto>>()
    val usersList: LiveData<ViewState<UserDto>>
        get() = _usersList

    private val _saveUser = MutableLiveData<ViewState<Boolean>>()
    val saveUser: LiveData<ViewState<Boolean>>
        get() = _saveUser

    fun getUsersList() {
        _usersList.value = ViewState.Loading()

        viewModelScope.launch {
            getUsersListUseCase(Unit).fold(::getUsersListFailure, ::getUsersListSuccess)
        }
    }

    fun saveUser(user: UserDto) {
        _saveUser.value = ViewState.Loading()
        viewModelScope.launch {
            saveUserUseCase(user).fold(::saveUserFailure, ::saveUserSuccess)
        }
    }

    private fun getUsersListSuccess(userList: UserDto) {
        _usersList.value = ViewState.Success(userList)
    }

    private fun getUsersListFailure(failure: Failure) {
        _usersList.value = ViewState.Error(proccessError(failure))
    }

    private fun saveUserSuccess(result: Boolean) {
        _saveUser.value = ViewState.Success(result)
    }

    private fun saveUserFailure(failure: Failure) {
        _saveUser.value = ViewState.Error(proccessError(failure))
    }

}