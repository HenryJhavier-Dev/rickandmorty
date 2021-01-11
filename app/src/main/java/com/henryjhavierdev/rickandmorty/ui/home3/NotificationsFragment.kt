package com.henryjhavierdev.rickandmorty.ui.home3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.FragmentNotificationsBinding
import com.henryjhavierdev.rickandmorty.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_notifications,container,
            false
        )

        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)


        // Assign the component to a property in the binding class.
        binding.viewModelNotification = notificationsViewModel
        // This is used so that the binding can observe LiveData updates
        //Sin esto no hace el enlace de las vistas con el view model (no hace el getRecyclerAdapter)
        binding.lifecycleOwner = this

        notificationsViewModel.characters.observe(viewLifecycleOwner, Observer {
            notificationsViewModel.setRecyclerAdapter(it)
        })

        return binding.root
    }
}