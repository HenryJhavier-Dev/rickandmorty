package com.henryjhavierdev.rickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.CharacterAdapter
import com.henryjhavierdev.rickandmorty.data.MediaProvider

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rv_home_character)
        recyclerView.layoutManager = LinearLayoutManager(context)

       //Obtiene lista de personales de la propertie result que esta dentro de character
       //Media provider es un object y loadCharacter devuelve datos cableados
        recyclerView.adapter = CharacterAdapter(MediaProvider.loadCharacter().results, 0)

        return root
    }


}