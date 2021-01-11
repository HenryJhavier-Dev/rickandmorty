package com.henryjhavierdev.rickandmorty.adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.BR
import com.henryjhavierdev.rickandmorty.model.Result
import com.henryjhavierdev.rickandmorty.viewmodel.NotificationsViewModel

class DataBindingCharacterAdapter(var notificationsViewModel: NotificationsViewModel, var layoutInflate: Int)
        :RecyclerView.Adapter<DataBindingCharacterAdapter.ViewHolderCharacter>(){

    var items: List<Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding:  ViewDataBinding = DataBindingUtil.inflate(
            layoutInflater, viewType, parent, false)
        return ViewHolderCharacter(binding)
    }



    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        holder.bind(notificationsViewModel, position)
    }

    //layoutInflate
    override fun getItemViewType(position: Int): Int {
        return layoutInflate
    }

    fun setCharacterList(phones: List<Result>){
        this.items = phones
        Log.e("setCharacterList", "entrooooo")
    }

    class ViewHolderCharacter (binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root){

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun bind(notificationsViewModel: NotificationsViewModel, position: Int){
            //normalmente no carga a la primera y el br se queda en rojo hasta que complilas
            //viewModelExample y positionExample son valores definidios en el
            // template que se pasa (view_character_item_data_bindig)
            //BR es una clase que se auto general
            binding?.setVariable(BR.viewModelExample, notificationsViewModel)
            binding?.setVariable(BR.positionExample, position)
            binding?.executePendingBindings()
        }
        /* lo que te ahorras
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

*/


    }
}