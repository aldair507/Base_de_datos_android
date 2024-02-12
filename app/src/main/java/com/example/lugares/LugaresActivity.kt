package com.example.lugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.basededatos.R
import com.example.basededatos.databinding.ActivityLugaresBinding
import com.example.basededatos.model.ManagerDb

class LugaresActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLugaresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLugaresBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val nombreLugar=intent.getStringExtra("nombre_lugar")
        val info= intent.getStringExtra("info")
        val latitude = intent.getDoubleExtra(
            "latitude",
            0.0
        )
        val longitude = intent.getDoubleExtra(
            "longitude",
            0.0
        )
        val img= intent.getIntExtra("img",0)
        binding.title.text = nombreLugar
        binding.info.text = info
        binding.img.setImageResource(img)

        binding.btnGuardar.setOnClickListener {
            val manager=ManagerDb(this)
            if (nombreLugar != null) {
                manager.insertData3(nombreLugar, longitude, latitude)
                Toast.makeText(this, "Datos gaurdados de $nombreLugar", Toast.LENGTH_SHORT).show()
            }

        }

    }
}