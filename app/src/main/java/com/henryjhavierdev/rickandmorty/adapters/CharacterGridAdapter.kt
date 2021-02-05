package com.henryjhavierdev.rickandmorty.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.ViewGridCharacterItemBinding
import com.henryjhavierdev.rickandmorty.utils.bindingInflate
import com.henryjhavierdev.rickandmorty.model.CharacterResultRs

class CharacterGridAdapter(
    private val listener: (CharacterResultRs) -> Unit
): RecyclerView.Adapter<CharacterGridAdapter.CharacterGridViewHolder>() {

    private val characterList: MutableList<CharacterResultRs> = mutableListOf()

    fun addData(newData: List<CharacterResultRs>) {
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
        private val listener: (CharacterResultRs) -> Unit
    ): RecyclerView.ViewHolder(dataBinding.root) {

        //region Public Methods
        fun bind(item: CharacterResultRs){

            dataBinding.character = item

            itemView.setOnClickListener { listener(item) }
        }

    }
}