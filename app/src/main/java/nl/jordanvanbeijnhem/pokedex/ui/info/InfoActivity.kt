package nl.jordanvanbeijnhem.pokedex.ui.info

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.jordanvanbeijnhem.pokedex.R
import nl.jordanvanbeijnhem.pokedex.model.Pokemon
import nl.jordanvanbeijnhem.pokedex.repository.PokemonRepository

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        val pokemon = intent.getParcelableExtra<Pokemon>("POKEMON")
        supportActionBar?.title = pokemon?.name?.capitalize()
        etNote.setText(pokemon?.note?.content)
        Glide.with(this).load(pokemon?.sprites?.frontSprite)
            .placeholder(R.drawable.poke_ball).into(ivSprite)

        when (pokemon?.id) {
            in 0L..10L -> tvNumber.text = "#00${pokemon?.id} - ${pokemon?.name?.capitalize()}"
            in 10L..100L -> tvNumber.text = "#0${pokemon?.id} - ${pokemon?.name?.capitalize()}"
            else -> tvNumber.text = "#${pokemon?.id} - ${pokemon?.name?.capitalize()}"
        }

        btnSave.setOnClickListener {
            pokemon?.note!!.content = etNote.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                PokemonRepository(this@InfoActivity).updatePokemon(pokemon)
            }
            onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
