package com.example.findmyhero.searchsuperhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.findmyhero.R
import com.example.findmyhero.databinding.FragmentSearchSuperHeroBinding

class SearchSuperHero: Fragment(){

    private lateinit var searchSuperHeroBinding : FragmentSearchSuperHeroBinding
    lateinit var searchSuperHeroViewModel : SearchSuperHeroViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchSuperHeroViewModel = ViewModelProvider(this)[SearchSuperHeroViewModel::class.java]
        searchSuperHeroBinding = FragmentSearchSuperHeroBinding.inflate(layoutInflater, container, false)
        searchSuperHeroViewModel.searchedSuperHero.observe(viewLifecycleOwner){response -> searchSuperHeroBinding.apply {
            linearLayoutContainer.visibility = View.VISIBLE
            cardTitleTextView.text = response.name
            view?.let {
                Glide.with(it.context)
                    .load(response.images?.lg)
                    .placeholder(R.drawable.ic_download_incomplete)
                    .error(R.drawable.ic_error)
                    .into(cardBackgroundImageView)
            }
        }}
        searchSuperHeroViewModel.message.observe(viewLifecycleOwner){message -> searchSuperHeroBinding.apply {
            errorResultTextView.visibility = View.VISIBLE
            errorResultTextView.text = message.toString()
        }}
        searchSuperHeroViewModel.navigateToDetailsFragment.observe(viewLifecycleOwner){
            if (it){
                searchSuperHeroViewModel.navigatedToDetailsFragment()
            val action = SearchSuperHeroDirections.actionSearchSuperHeroToHeroDetails(searchSuperHeroViewModel.superHeroResponse)
            findNavController().navigate(action)
        }}
        searchSuperHeroBinding.searchHeroButton.setOnClickListener{
            val textFromEditText = searchSuperHeroBinding.editTextTextPersonName.text.toString()
            searchSuperHeroViewModel.getSearchedHero(textFromEditText)
        }
        searchSuperHeroBinding.linearLayoutContainer.setOnClickListener{
            searchSuperHeroViewModel.navigatingToDetailFragment()
        }
        return(searchSuperHeroBinding.root)
    }
}