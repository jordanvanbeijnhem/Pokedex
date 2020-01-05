package nl.jordanvanbeijnhem.pokedex.ui.home

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
import nl.jordanvanbeijnhem.pokedex.R
import nl.jordanvanbeijnhem.pokedex.adapter.PokemonAdapter
import nl.jordanvanbeijnhem.pokedex.api.PokedexApi
import nl.jordanvanbeijnhem.pokedex.model.Pokemon


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val pokemon = arrayListOf<Pokemon>()
    private val pokemonAdapter = PokemonAdapter(pokemon)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(root)
        initViewModel()
        return root
    }

    private fun initViews(root: View) {
        val rvPokemon = root.findViewById<RecyclerView>(R.id.rvPokemon)
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvPokemon.layoutManager = layoutManager
        rvPokemon.adapter = pokemonAdapter
        rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                if (!homeViewModel.loading.value!! && firstVisiblePosition >= totalItemCount - (PokedexApi.PAGE_SIZE * 0.9) && firstVisiblePosition >= 0 && totalItemCount >= PokedexApi.PAGE_SIZE) {
                    homeViewModel.fetchNextPage()
                }
            }
        })
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.fetchedPokemon.observe(this, Observer {
            pokemon.addAll(it)
            pokemonAdapter.notifyDataSetChanged()
        })
        homeViewModel.loading.observe(this, Observer {
            val progressBar: ProgressBar? = activity?.toolbar?.findViewById(R.id.pbLoading)
            if (it) progressBar?.visibility = View.VISIBLE
            else progressBar?.visibility = View.GONE
        })
        homeViewModel.fetchNextPage()
    }
}