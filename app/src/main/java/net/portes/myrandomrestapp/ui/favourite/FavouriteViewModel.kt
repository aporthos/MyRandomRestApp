package net.portes.myrandomrestapp.ui.favourite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.BaseViewModel
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.usecases.DeleteUserUseCase

class FavouriteViewModel @ViewModelInject constructor(private val deleteUserUseCase: DeleteUserUseCase) :
    BaseViewModel() {

    private val _deleteUser = MutableLiveData<ViewState<Boolean>>()
    val deleteUser: LiveData<ViewState<Boolean>>
        get() = _deleteUser

    fun deleteUser(id: String) {
        _deleteUser.value = ViewState.Loading()
        viewModelScope.launch {
            deleteUserUseCase(id).fold(::deleteUserFailure, ::deleteUserSuccess)
        }
    }

    private fun deleteUserSuccess(result: Boolean) {
        _deleteUser.value = ViewState.Success(result)
    }

    private fun deleteUserFailure(failure: Failure) {
        _deleteUser.value = ViewState.Error(proccessError(failure))
    }
}