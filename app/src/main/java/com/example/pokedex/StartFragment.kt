package com.example.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.databinding.FragmentStartBinding
import com.example.pokedex.network.model.PokemonViewModel

class StartFragment : Fragment() {

    private val sharedViewModel: PokemonViewModel by activityViewModels()
    private var binding: FragmentStartBinding? = null
    private var adapter: PokemonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            startFragment = this@StartFragment
            viewModel = sharedViewModel
        }

        adapter = PokemonAdapter(PokemonAdapter.PokemonListener (
            { pokemon ->
                sharedViewModel.setPokemonScreen(pokemon)
        }
         ,context
        )
        )

        binding?.lista?.layoutManager = LinearLayoutManager(requireContext())
        binding?.lista?.adapter = adapter

        sharedViewModel.pokemonList.observe(viewLifecycleOwner, Observer { pokemonList ->
            if(pokemonList.isNotEmpty()){
                adapter?.submitList(pokemonList)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}