package net.portes.myrandomrestapp.ui.favourite

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import dagger.hilt.android.AndroidEntryPoint
import net.portes.myrandomrestapp.R
import net.portes.myrandomrestapp.databinding.FragmentFavouriteBinding
import net.portes.myrandomrestapp.ui.base.BaseFragment
import net.portes.shared.extensions.observe
import net.portes.shared.extensions.toast
import net.portes.shared.ui.base.OnClickListener
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.models.UserDto
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), OnClickListener<UserDto> {

    companion object {
        private const val COLLECTION_USERS = "users"
    }

    @Inject
    lateinit var firestore: FirebaseFirestore

    private val viewModel: FavouriteViewModel by viewModels()
    private lateinit var favouritesAdapter: FavouritesAdapter
    override fun getLayoutRes(): Int = R.layout.fragment_favourite

    override fun initializeView() {
        val query: Query = firestore.collection(COLLECTION_USERS).limit(10)
        val options: FirestoreRecyclerOptions<UserDto> = FirestoreRecyclerOptions.Builder<UserDto>()
            .setQuery(query, UserDto::class.java)
            .build()

        favouritesAdapter = object: FavouritesAdapter(options, this) {
            override fun onDataChanged() {
                super.onDataChanged()
                if (itemCount == 0) {
                    dataBinding().emptyFavouritesRelativeLayout.isVisible = true
                    dataBinding().favouritesRecyclerView.isGone = true
                } else {
                    dataBinding().emptyFavouritesRelativeLayout.isGone = true
                    dataBinding().favouritesRecyclerView.isVisible = true
                }
            }
            override fun onError(e: FirebaseFirestoreException) {
                super.onError(e)
                Timber.e("onError: $e")
            }
        }

        dataBinding().favouritesRecyclerView.adapter = favouritesAdapter
    }

    override fun initObservers() {
        observe(viewModel.deleteUser, ::resultDeleteUser)
    }

    override fun onStart() {
        super.onStart()
        favouritesAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        favouritesAdapter.stopListening()
    }

    override fun onClicked(v: View, item: UserDto) {
        when (v.id) {
            R.id.favouriteMaterialCardView -> {
                Timber.i("onClicked: -> $item")
            }
            R.id.deleteFavouriteImageView -> viewModel.deleteUser(item.id)
        }
    }

    private fun resultDeleteUser(result: ViewState<Boolean>) {
        when (result) {
            is ViewState.Loading -> showLoader()
            is ViewState.Success -> {
                hideLoader()
                requireContext().toast(R.string.message_delete_user_success)
            }
            is ViewState.Error -> {
                requireContext().toast(R.string.message_delete_user_failed)
                hideLoader()
            }
        }
    }
}