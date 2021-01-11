package com.henryjhavierdev.rickandmorty.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.model.Result

class CharacterAdapter(val item:List<Result> = emptyList()):RecyclerView.Adapter<CharacterAdapter.ViewHolderCharacter>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        val layoutInflater = LayoutInflater.from(parent.context).
            inflate(R.layout.item_character, parent, false)
        return ViewHolderCharacter(layoutInflater)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        holder.bind(character = item[position] )
    }


    class ViewHolderCharacter (val view: View):RecyclerView.ViewHolder(view){

        val name: TextView = view.findViewById(R.id.tv_home_name)
        val gender: TextView = view.findViewById(R.id.tv_home_gender)
        val species: TextView = view.findViewById(R.id.tv_home_species)
        val status: TextView = view.findViewById(R.id.tv_home_status)
        val avatar: ImageView = view.findViewById(R.id.iv_home_avatar)


        fun bind ( character: Result) {
            name.text = "Name: ${character.name}"
            gender.text = "Gender: ${character.gender}"
            species.text = "Species: ${character.species}"
            status.text = "Status: ${character.status}"

            Glide.with(avatar).load(character.image).into(avatar)

            view.setOnClickListener{
                Toast.makeText(view.context,"Character selected ${character.name}", Toast.LENGTH_LONG)
            }
        }



    }
}