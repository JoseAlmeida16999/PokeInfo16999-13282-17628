package ipca.trabalho.pokeinfo


import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.pokemon_list_view)

        val pokemonList = Pokemon.getPokemonsFromFile("pokeinfo.json", this)

        val adapter = PokemonAdapter(this, pokemonList)
        listView.adapter = adapter

        val context = this
        listView.setOnItemClickListener { _, _, position, _ ->
            // 1
            val selectedRecipe = pokemonList[position]

            // 2
            val detailIntent = PokeDetailActivity.newIntent(context, selectedRecipe)

            // 3
            startActivity(detailIntent)
        }

    }
}