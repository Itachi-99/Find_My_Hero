package com.example.findmyhero.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findmyhero.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    val listOfHeroes: LiveData<List<Superhero>>
        get() = _listOfHeroes
    private val _listOfHeroes = MutableLiveData<List<Superhero>>()
    val message = MutableLiveData<String>()
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val selectedSuperHero: LiveData<Superhero>
        get() = _selectedSuperHero
    private val _selectedSuperHero= MutableLiveData<Superhero>()
    init {
        getRandomSuperHeroes()
    }
    private fun getRandomSuperHeroes() {
        coroutineScope.launch {
            val getHeroesDeferred = SuperHeroAPI.superHeroApiService.randomTwentySuperHeroAsync()
            try {
                val result = getHeroesDeferred.await()
                _listOfHeroes.value = result
            } catch (e: Exception) {
               message.value = "Unable to Fetch Data Because of ${e.message}"
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displaySuperHeroDetails(superhero: Superhero){
        _selectedSuperHero.value = superhero
    }

}