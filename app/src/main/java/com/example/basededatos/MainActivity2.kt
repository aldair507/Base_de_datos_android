package com.example.basededatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.basededatos.databinding.ActivityMain2Binding
import com.example.basededatos.databinding.ActivityMainBinding
import com.example.basededatos.model.ManagerDb

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val enviar=binding.enviar

        enviar.setOnClickListener(){
            val code= binding.code.text.toString()
            val nombre= binding.nombre.text.toString()
            val apellido= binding.apellido.text.toString()
            val telefono=binding.telefono.text.toString()
            val direccion= binding.direccion.text.toString()
            val ciudad= binding.ciudad.text.toString()


            if (code.isEmpty()|| nombre.isEmpty() || apellido.isEmpty()|| telefono.isEmpty()||direccion.isEmpty()||ciudad.isEmpty()){
                Toast.makeText(this,"no puede ir vacio",Toast.LENGTH_SHORT).show()
            } else{
                val manager=ManagerDb(this)
                manager.inserData2(code.toInt(),nombre,apellido,telefono.toInt(),direccion,ciudad)
                Toast.makeText(this, "datos ingresados", Toast.LENGTH_SHORT).show()
            }

        }
    }

}