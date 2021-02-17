package com.henryjhavierdev.rickandmorty.adapters

import android.view.ViewGroup
import com.henryjhavierdev.domain.Character
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.ViewGridCharacterItemBinding
import com.henryjhavierdev.rickandmorty.parcelables.toCharacterResultParcelable
import com.henryjhavierdev.rickandmorty.utils.bindingInflate

class CharacterGridAdapter(
    private val listener: CharacterListener
): RecyclerView.Adapter<CharacterGridAdapter.CharacterGridViewHolder>() {

    private val characterList: MutableList<Character> = mutableListOf()

    fun addData(newData: List<Character>) {
        characterList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterGridViewHolder(
            parent.bindingInflate(R.layout.view_grid_character_item, false),
            listener
        )


    override fun getItemCount() = characterList.size

    override fun getItemId(position: Int): Long = characterList[position].id.toLong()

    override fun onBindViewHolder(holder: CharacterGridViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    class CharacterGridViewHolder(
        private val dataBinding: ViewGridCharacterItemBinding,
        private val listener: CharacterListener
    ): RecyclerView.ViewHolder(dataBinding.root) {

        //region Public Methods
        fun bind(item: Character){

            dataBinding.character = item
            itemView.setOnClickListener {
                listener.openCharacterDetail(item.toCharacterResultParcelable())
            }
        }

    }
}