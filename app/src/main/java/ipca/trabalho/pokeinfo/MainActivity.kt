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
        recyclerView_main.adapter = MainAdapter()

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

                //val gson = GsonBuilder().create()

                //gson.fromJson<>(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }


        })
    }
}

class PokeInformation(val creatures : List<Creature>)

class Creature(val id: Int, val name: String)

