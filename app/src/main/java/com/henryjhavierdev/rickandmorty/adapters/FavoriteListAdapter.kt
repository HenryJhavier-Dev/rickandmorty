package com.henryjhavierdev.rickandmorty.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.imagemanager.bindImageUrl
import com.henryjhavierdev.rickandmorty.parcelable.toCharacterResultRs
import com.henryjhavierdev.rickandmorty.databinding.ItemFavoriteCharacterBinding
import com.henryjhavierdev.rickandmorty.utils.bindingInflate
import kotlinx.android.synthetic.main.item_favorite_character.view.*

class FavoriteListAdapter(
    private val listener: FavoriteListener
): RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>() {

    private val characterList: MutableList<Character> = mutableListOf()

    fun updateData(newData: List<Character>) {
        characterList.clear()
        characterList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteListViewHolder(
            parent.bindingInflate(R.layout.item_favorite_character, false),
            listener
        )

    override fun getItemCount() = characterList.size

    override fun getItemId(position: Int): Long = characterList[position].id.toLong()

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    class FavoriteListViewHolder(
        private val dataBinding: ItemFavoriteCharacterBinding,
        private val listener: FavoriteListener
    ): RecyclerView.ViewHolder(dataBinding.root) {


        fun bind(item: Character){
            dataBinding.character = item
            itemView.character_image.bindImageUrl(
                url = item.image,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_broken_image_black
            )
            itemView.setOnClickListener {
                listener.onFavoriteListener(item.toCharacterResultRs())
            }
        }

    }
}
