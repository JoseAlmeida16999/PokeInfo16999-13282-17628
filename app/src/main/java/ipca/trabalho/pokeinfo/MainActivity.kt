package ipca.trabalho.pokeinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        //recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to fetch Json")

        val url ="https://api.myjson.com/bins/whx34"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

               val pokeInformation = gson.fromJson(body, PokeInformation::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(pokeInformation)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }


        })
    }
}

//{
//
//    "base_attack": 118,
//    "base_defense": 111,
//    "base_stamina": 128,
//    "form": "Normal",
//    "pokemon_id": 1,
//    "pokemon_name": "Bulbasaur"
//
//},
class PokeInformation(val creatures : List<Creature>)

class Creature(val base_attack: Int, val base_defense: Int, val base_stamina: Int, val form: String, val pokemon_id: Int, val pokemon_name : String)

