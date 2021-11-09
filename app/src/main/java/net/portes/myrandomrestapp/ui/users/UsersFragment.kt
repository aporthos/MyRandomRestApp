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
import net.portes.shared.extensions.goLocation
import net.portes.shared.extensions.makeCall
import net.portes.shared.extensions.observe
import net.portes.shared.extensions.sms
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.models.UserDto

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(), View.OnClickListener,
    EasyPermissions.PermissionCallbacks {

    companion object {
        private const val RC_CALL_PHONE = 1
    }

    private val viewModel: UsersViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.fragment_users

    private var userDto: UserDto? = null

    override fun initializeView() {
        dataBinding().layoutUser.callImageView.setOnClickListener(this)
        dataBinding().layoutUser.goLocationImageView.setOnClickListener(this)
        dataBinding().layoutUser.smsImageView.setOnClickListener(this)
        dataBinding().retryButton.setOnClickListener(this)
        dataBinding().usersSwipeRefreshLayout.setOnRefreshListener {
            dataBinding().usersSwipeRefreshLayout.isRefreshing = false
            viewModel.getUsersList()
        }
        viewModel.getUsersList()
    }

    override fun initObservers() {
        observe(viewModel.usersList, ::resultUsersList)
    }

    private fun resultUsersList(result: ViewState<UserDto>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                dataBinding().user = result.data
                userDto = result.data
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.callImageView -> {
                userDto?.cell?.let {
                    requestPermissions()
                }
            }
            R.id.goLocationImageView -> {
                userDto?.location?.coordinates?.let {
                    requireContext().goLocation(it.latitude, it.longitude)
                }
            }
            R.id.smsImageView -> {
                userDto?.cell?.let {
                    requireContext().sms(it)
                }
            }
            R.id.retryButton -> viewModel.getUsersList()
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
        userDto?.cell?.let {
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