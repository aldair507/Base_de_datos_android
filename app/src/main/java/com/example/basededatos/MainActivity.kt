package com.example.basededatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.basededatos.databinding.ActivityMainBinding
import com.example.basededatos.model.BdHelper
import com.example.basededatos.model.ManagerDb
import com.example.lugares.LugaresActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* val bdhepler=BdHelper(this)
        val db=bdhepler.writableDatabase // abro mi db en mode escritura
        Toast.makeText( this,"Base de datos creada", Toast.LENGTH_SHORT).show()
        db.close()
*/

        val boton = binding.boton
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










    }

}