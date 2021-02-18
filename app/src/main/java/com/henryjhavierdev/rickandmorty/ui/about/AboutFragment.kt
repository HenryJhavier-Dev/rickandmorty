package com.henryjhavierdev.rickandmorty.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.databinding.FragmentAboutBinding
import com.henryjhavierdev.rickandmorty.viewmodel.AboutViewModel


class AboutFragment : Fragment() {

    private val aboutViewModel: AboutViewModel by viewModels()
    lateinit var binding:FragmentAboutBinding

    override fun onCreateView( inflater: LayoutInflater,    container: ViewGroup?,  savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,container,false )
        binding.viewModelAbout = aboutViewModel
        binding.lifecycleOwner = this

        return  binding.root
    }

/*    //View model
    private val _text = MutableLiveData<String>().apply {
      value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    //ACtivity fragment
    dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })*/
}