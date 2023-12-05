package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemListBinding
import com.example.pokedex.network.Pokemon


class PokemonAdapter(
    private val touchListener: PokemonListener
): ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(DiffCallback) {


    class PokemonViewHolder(
        var binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(touchListener: PokemonListener, pokemon: Pokemon) {
            binding.pokemon = pokemon
            binding.touchListener = touchListener
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
        holder.bind(touchListener,pokemon)
    }
    class PokemonListener(
        val touchListener: (pokemon: Pokemon) -> Unit,
        private val context: Context?,
    ) : OnTouchListener {

        override fun onTouch(v: View, event : MotionEvent): Boolean {

           when (event.action) {
                MotionEvent.ACTION_DOWN ->   {
                    v.setBackgroundResource(R.drawable.empty_background)
                    v.updatePadding(left = (11.4 * context!!.resources.displayMetrics.density).toInt(),top = (7.4 * context.resources.displayMetrics.density).toInt(), bottom = 0, right = 0) // Set padding to 0 for both right and bottom
                    v.performClick()
                }              // Pressed
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.setBackgroundResource(R.drawable.backlist)
                    v.updatePadding(right = (11.4 * context!!.resources.displayMetrics.density).toInt(), bottom = (7.4 * context.resources.displayMetrics.density).toInt(),left = 0,top = 0)
                }

            }
            return false
        }

        fun onClick(pokemon:Pokemon) = touchListener(pokemon)

    }
}