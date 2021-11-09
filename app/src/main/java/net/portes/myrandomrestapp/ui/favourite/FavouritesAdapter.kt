package net.portes.myrandomrestapp.ui.favourite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import net.portes.myrandomrestapp.R
import net.portes.myrandomrestapp.databinding.ItemFavouriteBinding
import net.portes.shared.extensions.binding
import net.portes.shared.ui.base.OnClickListener
import net.portes.users.domain.models.UserDto

/**
 * @author amadeus.portes
 */
open class FavouritesAdapter(
    response: FirestoreRecyclerOptions<UserDto>,
    private val click: OnClickListener<UserDto>
) :
    FirestoreRecyclerAdapter<UserDto, FavouritesAdapter.ViewHolder>(response) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.binding<ItemFavouriteBinding>(R.layout.item_favourite)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: UserDto) {
        holder.bind(model, click)
        holder.binding.executePendingBindings()
    }

    class ViewHolder(val binding: ItemFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: UserDto, click: OnClickListener<UserDto>) {
            binding.user = model
            binding.listener = click
        }
    }

}