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

class UsersViewModel @ViewModelInject constructor(private val getUsersListUseCase: GetUsersListUseCase) :
    BaseViewModel() {

    private val _usersList = MutableLiveData<ViewState<UserDto>>()
    val usersList: LiveData<ViewState<UserDto>>
        get() = _usersList

    fun getUsersList() {
        _usersList.value = ViewState.Loading()

        viewModelScope.launch {
            getUsersListUseCase(Unit).fold(::getUsersListFailure, ::getUsersListSuccess)
        }
    }

    private fun getUsersListSuccess(ipcList: UserDto) {
        _usersList.value = ViewState.Success(ipcList)
    }

    private fun getUsersListFailure(failure: Failure) {
        _usersList.value = ViewState.Error(proccessError(failure))
    }

}