package com.example.findmyhero.herodetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.findmyhero.network.Superhero

class HeroDetailsViewModel(superhero: Superhero, app: Application): AndroidViewModel(app) {

 val superheroType = superhero

}