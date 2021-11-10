package net.portes.myrandomrestapp.ui.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.portes.myrandomrestapp.ui.mappers.UserUIMapper
import net.portes.myrandomrestapp.ui.models.UserUI
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.models.UserDto
import net.portes.users.domain.usecases.GetUsersListUseCase
import net.portes.users.domain.usecases.SaveUserUseCase
import java.security.PrivateKey

class UsersViewModel @ViewModelInject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val userUIMapper: UserUIMapper
) : BaseViewModel() {

    private val _usersList = MutableLiveData<ViewState<UserUI>>()
    val usersList: LiveData<ViewState<UserUI>>
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

    fun saveUser(user: UserUI) {
        _saveUser.value = ViewState.Loading()
        viewModelScope.launch {
            saveUserUseCase(userUIMapper.mapTo(user)).fold(::saveUserFailure, ::saveUserSuccess)
        }
    }

    private fun getUsersListSuccess(user: UserDto) {
        _usersList.value = ViewState.Success(userUIMapper.mapFrom(user))
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