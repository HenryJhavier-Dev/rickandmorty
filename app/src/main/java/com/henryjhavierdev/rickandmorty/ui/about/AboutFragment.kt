package com.henryjhavierdev.rickandmorty.ui.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.ArchitectureLibraryAdapter
import com.henryjhavierdev.rickandmorty.databinding.FragmentAboutBinding
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.viewmodel.AboutViewModel
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_home.*


class AboutFragment : Fragment() {

    private val aboutViewModel: AboutViewModel by viewModels()

    lateinit var binding:FragmentAboutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        binding.viewModelAbout = aboutViewModel
        binding.lifecycleOwner = this

        binding.aboutCopyright = aboutViewModel.aboutCopyright

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        aboutViewModel.aboutDescription.observe(viewLifecycleOwner, Observer {
            tv_about_description.text = it
        })

    }

    private fun validateEvents(event: Event<AboutViewModel.AboutNavigation>?) =
        event?.getContentIfNotHandled().let { navigation ->
            when(navigation){
                is AboutViewModel.AboutNavigation.GoUriPage -> goToUri(context, navigation.uri)
                is AboutViewModel.AboutNavigation.GoGitHub -> goToUri(context,navigation.uri)
            }
        }
    }
    fun goToUri(context: Context?, url: String){
        val uriUrl: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uriUrl)
        context?.startActivity(intent)
    }


