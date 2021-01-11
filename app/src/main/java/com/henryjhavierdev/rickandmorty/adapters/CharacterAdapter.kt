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
import com.henryjhavierdev.rickandmorty.helpers.generalToast
import com.henryjhavierdev.rickandmorty.helpers.loadImageViewFromUrl
import com.henryjhavierdev.rickandmorty.model.Result

class CharacterAdapter(val item:List<Result> = emptyList(), val typeTemplateItem:Int = 0 ) :RecyclerView.Adapter<CharacterAdapter.ViewHolderCharacter>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        //Dos tipos de template uno para usar como lista y otro como grid
        return when (typeTemplateItem){
            1 -> ViewHolderCharacter(LayoutInflater.from(parent.context).inflate(R.layout.view_grid_character_item, parent, false))
            else -> ViewHolderCharacter(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
        }
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

            //Glide tradicional
            //Glide.with(avatar).load(character.image).into(avatar)

            //Funsiones de extension
            //Si character es nulo manda vacio
            avatar.loadImageViewFromUrl(character.image ?: "")

            view.setOnClickListener{
                generalToast("Character selected ${character.name}")
            }
        }



    }
}