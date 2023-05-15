package com.example.findmyhero.searchsuperhero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.findmyhero.home.HomeFragmentDirections
import com.example.findmyhero.network.SuperHeroAPI
import com.example.findmyhero.network.Superhero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchSuperHeroViewModel() : ViewModel() {

    private var _searchedSuperHero = MutableLiveData<Superhero>()
    val searchedSuperHero: LiveData<Superhero>
        get() = _searchedSuperHero

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    var navigateToDetailsFragment = MutableLiveData<Boolean>()

    lateinit var superHeroResponse : Superhero

    fun getSearchedHero(heroName: String) {
        coroutineScope.launch {
            val getSearchHeroDeferred =
                SuperHeroAPI.superHeroApiService.getSearchedSuperHeroAsync(heroName)
            try {
                val result = getSearchHeroDeferred.await()
                _searchedSuperHero.value = result
                superHeroResponse = result
            } catch (e: Exception) {
                _message.value = "Failed to retrieve data because of ${e.message} "
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun navigatingToDetailFragment(){
        navigateToDetailsFragment.value = true
    }

    fun navigatedToDetailsFragment(){
        navigateToDetailsFragment.value = false
    }



}