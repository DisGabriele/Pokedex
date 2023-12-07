package com.example.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.databinding.FragmentStartBinding
import com.example.pokedex.network.model.PokemonViewModel
import com.example.pokedex.network.model.Status

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
                binding?.pokemonImage?.load(pokemon.img){
                    listener(
                        onError = {_,_ ->
                            Toast.makeText(context,getText(R.string.image_preview),Toast.LENGTH_SHORT).show()
                        }
                    )
                }
        }
         ,context
        )
        )

        binding?.lista?.layoutManager = LinearLayoutManager(requireContext())
        binding?.lista?.adapter = adapter


        sharedViewModel.status.observe(viewLifecycleOwner){
            if(sharedViewModel.statusCounter.value == false && sharedViewModel.status.value != Status.LOADING){
                Toast.makeText(context,getText(R.string.connection_error),Toast.LENGTH_SHORT).show()
                sharedViewModel.toggleStatusCounter()
            }
        }

        sharedViewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->

            if (pokemonList.isNotEmpty()) {
                adapter?.submitList(pokemonList)

                if (sharedViewModel.pokemonScreen.value == null) {
                    sharedViewModel.setPokemonScreen(pokemonList.get(0))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}