package com.henryjhavierdev.rickandmorty.ui.home2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.henryjhavierdev.rickandmorty.adapters.ViewBindingCharacterAdapter
import com.henryjhavierdev.rickandmorty.data.MediaProvider
import com.henryjhavierdev.rickandmorty.databinding.FragmentDashboardBinding
import com.henryjhavierdev.rickandmorty.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {

    // Use the 'by viewModels()' Kotlin property delegate from the activity-ktx artifact
    private val dashboardViewModel: DashboardViewModel by viewModels()
    //MVVM otra forma de declarar private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView( inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(layoutInflater)

        //MVVM otra forma dashboardViewModel =
        //        ViewModelProvider(this).get(DashboardViewModel::class.java)

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })

        //El layoutManager se configura en el xml
        //app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        binding.rvCharacterTwo.adapter = ViewBindingCharacterAdapter(MediaProvider.loadCharacter().results)

        //return root
        return  binding.root
    }
}