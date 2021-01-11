package com.henryjhavierdev.rickandmorty.data

import androidx.annotation.WorkerThread
import com.henryjhavierdev.rickandmorty.model.Character
import com.henryjhavierdev.rickandmorty.model.Result

//Idenfitica que esta ejecucion no se debe ejecutar en el hilo principal
@WorkerThread
object MediaProvider {
    fun loadCharacter(): Character {
        return Character(null,
            listOf(
                Result(1,"Rick Sanchez", "Alive", "Human",null,"Male", null, null, "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
                Result(2,"Morty Smith", "Alive", "Human",null,"Male", null, null, "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
                Result(3,"Summer Smith", "Alive", "Human",null,"Female", null, null, "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
                Result(4,"Beth Smith", "Alive", "Human",null,"Female", null, null, "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
                Result(5,"Jerry Smith", "Alive", "Human",null,"Male", null, null, "https://rickandmortyapi.com/api/character/avatar/5.jpeg"),
                Result(6,"Abadango Cluster Princess", "Alive", "Alien",null,"Female", null, null, "https://rickandmortyapi.com/api/character/avatar/6.jpeg"),
                Result(7,"Abradolf Lincler", "unknown", "Human","Genetic experiment","Male", null, null, "https://rickandmortyapi.com/api/character/avatar/7.jpeg"),
                Result(8,"Adjudicator Rick", "Dead", "Human",null,"Male", null, null, "https://rickandmortyapi.com/api/character/avatar/8.jpeg")
            )
        )
    }

}