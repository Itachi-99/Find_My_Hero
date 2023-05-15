package com.example.findmyhero.herodetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findmyhero.network.Superhero

class HeroDetailsViewModelFactory(private val superhero: Superhero, private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeroDetailsViewModel(superhero,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}