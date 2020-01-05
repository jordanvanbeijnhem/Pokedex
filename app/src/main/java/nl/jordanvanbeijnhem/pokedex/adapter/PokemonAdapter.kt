package nl.jordanvanbeijnhem.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.jordanvanbeijnhem.pokedex.R
import nl.jordanvanbeijnhem.pokedex.model.Pokemon
import nl.jordanvanbeijnhem.pokedex.repository.PokemonRepository

class PokemonAdapter(private val pokemon: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(pokemon[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pokemon: Pokemon) {
            setFavoriteImage(pokemon)
            itemView.ivFavorite.setOnClickListener {
                pokemon.isFavorite = !pokemon.isFavorite
                Toast.makeText(
                    context,
                    context.getString(
                        if (pokemon.isFavorite) R.string.favorite_add else R.string.favorite_remove,
                        pokemon.name.capitalize()
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                setFavoriteImage(pokemon)
                CoroutineScope(Dispatchers.IO).launch {
                    PokemonRepository(context).updatePokemon(pokemon)
                }
            }
            itemView.tvName.text = pokemon.name.capitalize()
            Glide.with(context).load(pokemon.sprites.frontSprite)
                .placeholder(R.drawable.poke_ball).into(itemView.ivSprite)
        }

        private fun setFavoriteImage(pokemon: Pokemon) {
            if (pokemon.isFavorite) itemView.ivFavorite.setImageResource(R.drawable.ic_star_gold_24dp)
            else itemView.ivFavorite.setImageResource(R.drawable.ic_star_border_gold_24dp)
        }
    }
}