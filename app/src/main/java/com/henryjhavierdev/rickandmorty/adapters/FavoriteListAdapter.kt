package com.henryjhavierdev.rickandmorty.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.imagemanager.bindImageUrl
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.ViewFavoriteCharacterItemBinding
import com.henryjhavierdev.rickandmorty.parcelables.toCharacterResultParcelable
import com.henryjhavierdev.rickandmorty.utils.bindingInflate
import kotlinx.android.synthetic.main.view_favorite_character_item.view.*

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
            parent.bindingInflate(R.layout.view_favorite_character_item, false),
            listener
        )

    override fun getItemCount() = characterList.size

    override fun getItemId(position: Int): Long = characterList[position].id.toLong()

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    class FavoriteListViewHolder(
        private val dataBinding: ViewFavoriteCharacterItemBinding,
        private val listener: FavoriteListener
    ): RecyclerView.ViewHolder(dataBinding.root) {


        fun bind(item: Character){
            dataBinding.character = item
            itemView.iv_favorite_character.bindImageUrl(
                url = item.image,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_broken_image_black
            )
            itemView.setOnClickListener {
                listener.onFavoriteListener(item.toCharacterResultParcelable())
            }
        }

    }
}
