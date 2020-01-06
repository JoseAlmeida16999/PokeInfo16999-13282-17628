package ipca.trabalho.pokeinfo

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

class Pokemon(
    val name: String,
    val imageUrl: String,
    val url: String,
    val type1: String,
    val type2: String,

    val id: String) {

    companion object {

        fun getPokemonsFromFile(filename: String, context: Context): ArrayList<Pokemon> {
            val pokemonList = ArrayList<Pokemon>()

            try {
                // Load data
                val jsonString = loadJsonFromAsset("pokeinfo.json", context)
                val json = JSONObject(jsonString)
                val pokemons = json.getJSONArray("pokemons")

                // Get Pokemon objects from data
                (0 until pokemons.length()).mapTo(pokemonList) {
                    Pokemon(pokemons.getJSONObject(it).getString("name"),
                        pokemons.getJSONObject(it).getString("imageUrl"),
                        pokemons.getJSONObject(it).getString("url"),
                        pokemons.getJSONObject(it).getString("type1"),
                        pokemons.getJSONObject(it).getString("type2"),
                        pokemons.getJSONObject(it).getString("id"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return pokemonList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}