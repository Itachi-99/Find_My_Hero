package com.example.findmyhero.herodetails

import android.app.Application
import android.graphics.SumPathEffect
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.findmyhero.databinding.FragmentHeroDetailsBinding
import com.example.findmyhero.network.Superhero

class HeroDetails: Fragment(){
    private lateinit var heroDetailsFragmentBinding: FragmentHeroDetailsBinding
    private lateinit var heroDetailsViewModel: HeroDetailsViewModel
    private lateinit var heroDetailsViewModelFactory: HeroDetailsViewModelFactory
    private val navigationArgs : HeroDetailsArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val superhero = navigationArgs.superHeroDetails
        val application = requireNotNull(activity).application
        heroDetailsFragmentBinding = FragmentHeroDetailsBinding.inflate(layoutInflater, container, false)
        heroDetailsViewModelFactory = HeroDetailsViewModelFactory(superhero, application)
        heroDetailsViewModel = ViewModelProvider(this, heroDetailsViewModelFactory)[HeroDetailsViewModel::class.java]
        heroDetailsFragmentBinding.heroDetailsViewModel= heroDetailsViewModel
        heroDetailsFragmentBinding.lifecycleOwner = viewLifecycleOwner
        return heroDetailsFragmentBinding.root
    }
}