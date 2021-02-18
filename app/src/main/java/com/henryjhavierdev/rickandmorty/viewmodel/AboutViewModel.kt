package com.henryjhavierdev.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    var profileImage = gitHubImageProfile


    companion object {
        const val gitHubProfile = "https://github.com/HenryJhavier-Dev"
        const val gitLinkedinProfile = "https://www.linkedin.com/in/henryjhavier/"
        const val gitHubImageProfile = "https://avatars.githubusercontent.com/u/75500246?s=400&u=2efbe848afd346d701692040b1ad86419d64ba4f&v=4"
    }
}


