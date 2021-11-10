package net.portes.myrandomrestapp.ui.favourite

import android.Manifest
import android.view.View
import androidx.navigation.fragment.navArgs
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import net.portes.myrandomrestapp.R
import net.portes.myrandomrestapp.databinding.FragmentFavouriteDetailBinding
import net.portes.myrandomrestapp.ui.base.BaseFragment
import net.portes.myrandomrestapp.ui.models.UserUI
import net.portes.shared.extensions.goLocation
import net.portes.shared.extensions.makeCall
import net.portes.shared.extensions.sms

/**
 * @author amadeus.portes
 */
class FavouriteDetailFragment : BaseFragment<FragmentFavouriteDetailBinding>(),
    View.OnClickListener, EasyPermissions.PermissionCallbacks {

    companion object {
        private const val RC_CALL_PHONE = 1
    }

    private val args: FavouriteDetailFragmentArgs by navArgs()
    private lateinit var user: UserUI

    override fun getLayoutRes(): Int = R.layout.fragment_favourite_detail

    override fun initializeView() {
        dataBinding().layoutUser.callImageView.setOnClickListener(this)
        dataBinding().layoutUser.goLocationImageView.setOnClickListener(this)
        dataBinding().layoutUser.smsImageView.setOnClickListener(this)

        user = args.user
        dataBinding().user = user
    }

    override fun initObservers() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.callImageView -> {
                requestPermissions()
            }
            R.id.goLocationImageView -> {
                user.location.coordinates.let {
                    requireContext().goLocation(it.latitude, it.longitude)
                }
            }
            R.id.smsImageView -> requireContext().sms(user.cell)
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
        requireContext().makeCall(user.cell)
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