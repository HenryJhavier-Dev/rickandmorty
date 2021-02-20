package com.henryjhavierdev.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryjhavierdev.rickandmorty.adapters.ArchitectureLibraryAdapter
import com.henryjhavierdev.rickandmorty.presentation.Event

class AboutViewModel : ViewModel() {

    var profileImage = gitHubImageProfile

    private val _aboutDescription = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val aboutDescription: LiveData<String> = _aboutDescription

    var aboutCopyright = "  Rick and Morty is an American adult animated science fiction sitcom" +
    " created by Justin Roiland and Dan Harmon for Cartoon Network's nighttime Adult " +
    "Swim programming block. \n \n" +
    "   The Rick and Morty API is a RESTful development by Axel Fuhrmann "

    private val _events = MutableLiveData<Event<AboutNavigation>>()
    val events: LiveData<Event<AboutNavigation>> get() = _events

    private lateinit var architectureLibraryAdapter: ArchitectureLibraryAdapter

    init {
        _aboutDescription.value = "  A app based on the television show Rick and Morty, where you " +
                "can access information on their characters and in which episodes they have participated." +
                " \n\n  Illustrating Android development best practices with Android Jetpack."

    }
    fun onClickedGoGitHub(){
        _events.value = Event(AboutNavigation.GoGitHub(gitHubProfile))
    }

    fun onClickedGoApi(){
        _events.value = Event(AboutNavigation.GoUriPage(rickandmortyapi))
    }

    fun onClickedGoLinkedIn(){
        _events.value = Event(AboutNavigation.GoGitHub(gitLinkedinProfile))
    }

    private fun loadDada(): ArrayList<String>{
        return  arrayListOf("Data binding","Lifecycles","LifeData", "Navigation", "Room","ViewModel","Android KTX")
    }

    fun getRecyclerAdapterTwo(): ArchitectureLibraryAdapter?{
        architectureLibraryAdapter = ArchitectureLibraryAdapter(loadDada())
        return architectureLibraryAdapter
    }


    sealed class AboutNavigation {
        data class GoUriPage(val uri: String) : AboutNavigation()
        data class GoGitHub(val uri: String) : AboutNavigation()
    }

    companion object {
        const val gitHubProfile = "https://github.com/HenryJhavier-Dev"
        const val rickandmortyapi = "https://rickandmortyapi.com/about/"
        const val gitLinkedinProfile = "https://www.linkedin.com/in/henryjhavier/"
        const val gitHubImageProfile = "https://avatars.githubusercontent.com/u/75500246?s=400&u=2efbe848afd346d701692040b1ad86419d64ba4f&v=4"
    }
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
