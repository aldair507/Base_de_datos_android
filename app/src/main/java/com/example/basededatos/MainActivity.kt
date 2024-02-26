package com.example.basededatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.basededatos.consumo.PokemonApiService
import com.example.basededatos.databinding.ActivityMainBinding
import com.example.basededatos.model.BdHelper
import com.example.basededatos.model.ManagerDb
import com.example.basededatos.model.Pokemon
import com.example.basededatos.model.Pokemones
import com.example.lugares.LugaresActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val text=binding.text
        val image=binding.image

        val retrifit=Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service=retrifit.create(PokemonApiService::class.java)
        val call=service.getPokemonById()

        call.enqueue(object: Callback<Pokemones>{
            override fun onResponse(call: Call<Pokemones>, response: Response<Pokemones>) {

                if(response.isSuccessful){
                    val pokemones: Pokemones? = response.body()

                    Toast.makeText(this@MainActivity, "el consumo $pokemones",Toast.LENGTH_SHORT).show()
                    text.text=pokemones.toString()
                    Picasso.get()
                        .load("https://centrodepsicologiademadrid.es/wp-content/uploads/2018/11/rabiaytristeza.jpg")
                        .into(image);
                } else{

                }
            }



            override fun onFailure(call: Call<Pokemones>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

       /* val bdhepler=BdHelper(this)
        val db=bdhepler.writableDatabase // abro mi db en mode escritura
        Toast.makeText( this,"Base de datos creada", Toast.LENGTH_SHORT).show()
        db.close()
*/

   /*     val boton = binding.boton
        val manager = ManagerDb(this)
        boton.setOnClickListener {
            val cod = binding.dep.text.toString()
            val nombre = binding.nombre.text.toString()
            val codedep = binding.codigodep.text.toString()




            manager.insertData(cod.toInt(), nombre, codedep.toInt())

            Toast.makeText(this, "Se gurdo la info", Toast.LENGTH_SHORT).show()


        }

        binding.btnVer.setOnClickListener{
            val intent=Intent(this,Listview::class.java )
            startActivity(intent)
        }

*/
    }



}





