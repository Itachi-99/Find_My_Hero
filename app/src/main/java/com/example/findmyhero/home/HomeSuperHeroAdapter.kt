package com.example.findmyhero.home

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findmyhero.R

import com.example.findmyhero.databinding.HomeHeroListBinding
import com.example.findmyhero.network.Superhero

class HomeSuperHeroAdapter(private val listItems: List<Superhero>, private val listener:OnSuperHeroClickListener) :
    RecyclerView.Adapter<HomeSuperHeroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeHeroListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.bind(item, listener)
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHeroDetails(item)
            it.findNavController().navigate(action)
        }
    }

    inner class ViewHolder(private val binding: HomeHeroListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Superhero, listener: OnSuperHeroClickListener) {
            binding.apply {
                root.setOnClickListener { listener.onItemClicked(adapterPosition) }
                cardTitleTextView.text = item.name
                Glide.with(itemView.context)
                    .load(item.images?.lg)
                    .placeholder(R.drawable.ic_download_incomplete)
                    .error(R.drawable.ic_error)
                    .into(cardBackgroundImageView)
            }

        }

    }
}
