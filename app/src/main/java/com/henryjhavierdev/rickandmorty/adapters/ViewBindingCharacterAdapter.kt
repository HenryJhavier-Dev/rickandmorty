package com.henryjhavierdev.rickandmorty.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.ViewCharacterItemDataBindingBinding
import com.henryjhavierdev.rickandmorty.helpers.generalToast
import com.henryjhavierdev.rickandmorty.helpers.loadImageViewFromUrl
import com.henryjhavierdev.rickandmorty.model.Result

class ViewBindingCharacterAdapter(val items:List<Result> = emptyList() )
    :RecyclerView.Adapter<ViewBindingCharacterAdapter.ViewHolderCharacterViewBinding>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacterViewBinding {
        return ViewHolderCharacterViewBinding(LayoutInflater.from(parent.context).
        inflate(R.layout.view_grid_character_item, parent, false))

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolderCharacterViewBinding, position: Int) {
        holder.bind(character = items[position] )
    }


    class ViewHolderCharacterViewBinding (val view: View):RecyclerView.ViewHolder(view){

        private val binding = ViewCharacterItemDataBindingBinding.bind(view)

        fun bind ( character: Result) = with(binding){

            tvHomeName.text = "Name: ${character.name}"
            tvHomeGender.text = "Gender: ${character.gender}"
            tvHomeSpecies.text = "Species: ${character.species}"
            tvHomeStatus.text = "Status: ${character.status}"

            ivHomeAvatar.loadImageViewFromUrl(character.image ?: "")

            view.setOnClickListener{
                generalToast("Character selected ${character.name}")
            }
        }



    }
}