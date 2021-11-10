package net.portes.myrandomrestapp.ui.users

import android.Manifest
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import net.portes.myrandomrestapp.R
import net.portes.myrandomrestapp.databinding.FragmentUsersBinding
import net.portes.myrandomrestapp.ui.base.BaseFragment
import net.portes.myrandomrestapp.ui.models.UserUI
import net.portes.shared.extensions.*
import net.portes.shared.ui.base.ViewState

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(), View.OnClickListener,
    EasyPermissions.PermissionCallbacks {

    companion object {
        private const val RC_CALL_PHONE = 1
    }

    private val viewModel: UsersViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.fragment_users

    private var user: UserUI? = null

    override fun initializeView() {
        dataBinding().layoutUser.callImageView.setOnClickListener(this)
        dataBinding().layoutUser.goLocationImageView.setOnClickListener(this)
        dataBinding().layoutUser.smsImageView.setOnClickListener(this)
        dataBinding().retryButton.setOnClickListener(this)
        dataBinding().saveFavouriteFab.setOnClickListener(this)
        dataBinding().usersSwipeRefreshLayout.setOnRefreshListener {
            dataBinding().usersSwipeRefreshLayout.isRefreshing = false
            viewModel.getUsersList()
        }
        viewModel.getUsersList()
    }

    override fun initObservers() {
        observe(viewModel.usersList, ::resultUsersList)
        observe(viewModel.saveUser, ::resultSaveUser)
    }

    private fun resultUsersList(result: ViewState<UserUI>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                dataBinding().user = result.data
                user = result.data
                dataBinding().usersNestedScrollView.isVisible = true
                dataBinding().errorConnectionRelativeLayout.isGone = true
            }
            is ViewState.Error -> {
                dataBinding().usersNestedScrollView.isGone = true
                dataBinding().errorConnectionRelativeLayout.isVisible = true
                hideLoader()
            }
        }
    }

    private fun resultSaveUser(result: ViewState<Boolean>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                requireContext().toast(R.string.message_save_user_success)
            }
            is ViewState.Error -> {
                requireContext().toast(R.string.message_save_user_failed)
                hideLoader()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.callImageView -> {
                user?.cell?.let {
                    requestPermissions()
                }
            }
            R.id.goLocationImageView -> {
                user?.location?.coordinates?.let {
                    requireContext().goLocation(it.latitude, it.longitude)
                }
            }
            R.id.smsImageView -> {
                user?.cell?.let {
                    requireContext().sms(it)
                }
            }
            R.id.retryButton -> viewModel.getUsersList()
            R.id.saveFavouriteFab -> {
                user?.let {
                    viewModel.saveUser(it)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        toCallPhone()
    }

    private fun toCallPhone() {
        user?.cell?.let {
            requireContext().makeCall(it)
        }
    }

    private fun requestPermissions() {
        if (hasCallPhone()) {
            toCallPhone()
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.message_permissions_call_phone),
                RC_CALL_PHONE,
                Manifest.permission.CALL_PHONE
            )
        }
    }

    private fun hasCallPhone(): Boolean =
        EasyPermissions.hasPermissions(requireContext(), Manifest.permission.CALL_PHONE)
}