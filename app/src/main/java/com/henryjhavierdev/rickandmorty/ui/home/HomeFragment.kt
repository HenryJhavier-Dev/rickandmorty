package com.henryjhavierdev.rickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.CharacterAdapter
import com.henryjhavierdev.rickandmorty.data.MediaProvider
import com.henryjhavierdev.rickandmorty.viewmodel.home.HomeViewModel

class HomeFragment : Fragment() {

    // Use the 'by viewModels()' Kotlin property delegate from the activity-ktx artifact
    //MVVM otra forma de declarar  private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeViewModel: HomeViewModel
    //private lateinit var application: RickAndMortyApplication

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        homeViewModel =
                ViewModelProvider(this, HomeViewModel.HomeViewModelFactory(
                        appication = activity.application
                )).get(HomeViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rv_home_character)
        recyclerView.layoutManager = LinearLayoutManager(context)

        homeViewModel.characters.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = CharacterAdapter(it.results)
        })
       //Obtiene lista de personales de la propertie result que esta dentro de character
       //Media provider es un object y loadCharacter devuelve datos cableados
        //recyclerView.adapter = CharacterAdapter(MediaProvider.loadCharacter().results)

        return root
    }


}