package com.example.basededatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.Toast
import com.example.basededatos.databinding.ActivityMainBinding
import com.example.basededatos.model.BdHelper
import com.example.basededatos.model.ManagerDb

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

        boton.setOnClickListener {
            val dep = binding.dep.text.toString().toInt()
            val nombre = binding.nombre.text.toString()
            val codedep = binding.codigodep.text.toString().toInt()

            val manager = ManagerDb(this)
            manager.insertData(dep, nombre, codedep)

            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show()
        }










    }
    
}