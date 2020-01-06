package ipca.trabalho.pokeinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso


class PokemonAdapter (private val context : Context, private val dataSource : ArrayList<Pokemon>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object{
        private val LABEL_COLORS = hashMapOf(
            "Normal" to R.color.colorNormal,
            "Fire" to R.color.colorFire,
            "Water" to R.color.colorWater,
            "Electric" to R.color.colorElectric,
            "Grass" to R.color.colorGrass,
            "Ice" to R.color.colorIce,
            "Fighting" to R.color.colorFighting,
            "Poison" to R.color.colorPoison,
            "Ground" to R.color.colorGround,
            "Flying" to R.color.colorFlying,
            "Psychic" to R.color.colorPsychic,
            "Bug" to R.color.colorBug,
            "Rock" to R.color.colorRock,
            "Ghost" to R.color.colorGhost,
            "Dragon" to R.color.colorDragon,
            "Dark" to R.color.colorDark,
            "Steel" to R.color.colorSteel,
            "Fairy" to R.color.colorFairy

        )
    }

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_pokemon, parent, false)

        val nameTextView = rowView.findViewById(R.id.pokemon_list_name) as TextView

        val typeTextView = rowView.findViewById(R.id.pokemon_list_type) as TextView

        val typeTextView2 = rowView.findViewById(R.id.pokemon_list_type2) as TextView

        val idTextView =rowView.findViewById(R.id.pokemon_list_id) as TextView

        val imageImageView = rowView.findViewById(R.id.pokemon_list_image) as ImageView

        val pokemon = getItem(position) as Pokemon

        nameTextView.text = pokemon.name
        typeTextView.text = pokemon.type1
        typeTextView2.text = pokemon.type2
        idTextView.text = pokemon.id

        Picasso.with(context).load(pokemon.imageUrl).placeholder(R.mipmap.ic_launcher).into(imageImageView)

        typeTextView.setTextColor( ContextCompat.getColor(context, LABEL_COLORS[pokemon.type1] ?: R.color.colorPrimary))
        typeTextView2.setTextColor( ContextCompat.getColor(context, LABEL_COLORS[pokemon.type2] ?: R.color.colorPrimary))

        return rowView
    }


}