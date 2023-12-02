package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemListBinding
import com.example.pokedex.network.Pokemon

class PokemonAdapter(
    private val clickListener: PokemonListener,
): ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DiffCallback) {


    class PokemonViewHolder(
        var binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {



        fun bind(clickListener: PokemonListener, pokemon: Pokemon) {
            binding.pokemon = pokemon
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)

        return PokemonViewHolder(ItemListBinding.inflate(adapterLayout,parent,false))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {

        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.num == newItem.num
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(clickListener,pokemon)
    }

    class PokemonListener(val clickListener: (pokemon: Pokemon) -> Unit) {
        fun onClick(pokemon: Pokemon) = clickListener(pokemon)
    }
}