package com.example.findmyhero.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.findmyhero.R
import com.example.findmyhero.databinding.FragmentHomeBinding
import com.example.findmyhero.network.Superhero

class HomeFragment : Fragment() {
    private lateinit var homeFragmentBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        homeFragmentBinding.titleTextView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchSuperHero)
        }
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.listOfHeroes.observe(
            viewLifecycleOwner
        ) { heroList ->
            homeFragmentBinding.homeRecyclerView.adapter = HomeSuperHeroAdapter(heroList, object : OnSuperHeroClickListener{
                override fun onItemClicked(position: Int) {
                    Toast.makeText(requireContext(), "K xa adarsha dai", Toast.LENGTH_SHORT).show()
                }
            })
        }
        homeViewModel.message.observe(viewLifecycleOwner) { newMessage ->
            Toast.makeText(
                requireContext(),
                newMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
        return homeFragmentBinding.root
    }
}