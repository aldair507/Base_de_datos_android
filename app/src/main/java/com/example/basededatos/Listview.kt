package com.example.basededatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.basededatos.databinding.ActivityListviewBinding
import com.example.basededatos.model.Ciudad
import com.example.basededatos.model.ManagerDb

class Listview : AppCompatActivity() {
    private lateinit var binding: ActivityListviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager=ManagerDb(this)
        val arrayCiudad= manager.getData()

        val listCiudad=binding.ListView
        val arrayAdapter=ArrayAdapter<Ciudad>(this,android.R.layout.simple_expandable_list_item_1,arrayCiudad)
        listCiudad.adapter=arrayAdapter
        Toast.makeText(this,"datos listados.",Toast.LENGTH_SHORT).show()
    }
}