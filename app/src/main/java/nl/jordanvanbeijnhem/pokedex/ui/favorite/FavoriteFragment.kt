package nl.jordanvanbeijnhem.pokedex.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import nl.jordanvanbeijnhem.pokedex.R
import nl.jordanvanbeijnhem.pokedex.adapter.PokemonAdapter
import nl.jordanvanbeijnhem.pokedex.model.Pokemon

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private val pokemon = arrayListOf<Pokemon>()
    private val pokemonAdapter = PokemonAdapter(pokemon)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        initViews(root)
        initViewModel()
        return root
    }

    private fun initViews(root: View) {
        val rvFavoritePokemon = root.findViewById<RecyclerView>(R.id.rvFavoritePokemon)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvFavoritePokemon.layoutManager = layoutManager
        rvFavoritePokemon.adapter = pokemonAdapter
    }

    private fun initViewModel() {
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        favoriteViewModel.favoritePokemon.observe(this, Observer {
            pokemon.clear()
            pokemon.addAll(it)
            pokemonAdapter.notifyDataSetChanged()

            if (pokemon.isEmpty()) {
                tvEmpty.visibility = View.VISIBLE
            } else {
                tvEmpty.visibility = View.GONE
            }
        })
        favoriteViewModel.loading.observe(this, Observer {
            val progressBar: ProgressBar? = activity?.toolbar?.findViewById(R.id.pbLoading)
            if (it) progressBar?.visibility = View.VISIBLE
            else progressBar?.visibility = View.GONE
        })
    }
}