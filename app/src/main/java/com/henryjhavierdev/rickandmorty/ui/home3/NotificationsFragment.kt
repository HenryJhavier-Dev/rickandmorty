package com.henryjhavierdev.rickandmorty.ui.home3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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


        return binding.root
    }
}