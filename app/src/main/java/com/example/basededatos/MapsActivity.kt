package com.example.basededatos

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.basededatos.databinding.ActivityMapsBinding
import com.example.lugares.LugaresActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var spinner: Spinner

    private var currentMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar las vistas después de inflar el diseño
        spinner = binding.spinner
//
//        hideAllButtons()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                val location = when (position) {
                    1 -> LatLng(2.483302497351136, -76.561758126264)  // Sena
                    2 -> LatLng(2.4419477932001885, -76.6062739) // Parque Caldas
                    3 -> LatLng(2.444638282857905, -76.60015846325189)  // Morro
                    else -> null
                }

                location?.let {
                    handleLocation(it)
                } ?: run {
                    Toast.makeText(this@MapsActivity, "Ubicacion Invalida", Toast.LENGTH_SHORT).show()
                }

                binding.info.setOnClickListener {
//                    hideAllButtons()
                    if (position == 1) {
                        val intent = Intent(this@MapsActivity, LugaresActivity::class.java)
                        intent.putExtra("nombre_lugar", "Sena")
                        intent.putExtra("img", R.drawable.sena)
                        intent.putExtra("latitude", 2.483302497351136)
                        intent.putExtra("longitude", -76.561758126264)
                        intent.putExtra("info", "lugar donde se forman jóvenes para el futuro")
                        mediaPlayer?.release() // Liberar el reproductor de medios anterior si existe
                        mediaPlayer = MediaPlayer.create(this@MapsActivity, R.raw.sena)
                        mediaPlayer?.start()
                        startActivity(intent)
                    } else if (position == 2) {
                        val intent = Intent(this@MapsActivity, LugaresActivity::class.java)
                        intent.putExtra("nombre_lugar", "parque caldas")
                        intent.putExtra("img", R.drawable.parque)
                        intent.putExtra("latitude", 2.4419477932001885)
                        intent.putExtra("longitude", -76.6062739)
                        intent.putExtra("info", "Parque central de la ciudad de popayan")
                        mediaPlayer?.release() // Liberar el reproductor de medios anterior si existe
                        mediaPlayer = MediaPlayer.create(this@MapsActivity, R.raw.caldas)
                        mediaPlayer?.start()
                        startActivity(intent)
                    } else if (position == 3) {
                        val intent = Intent(this@MapsActivity, LugaresActivity::class.java)
                        intent.putExtra("nombre_lugar", "El morro")
                        intent.putExtra("img", R.drawable.morro)
                        intent.putExtra("latitude", 2.444638282857905)
                        intent.putExtra("longitude", -76.60015846325189)
                        intent.putExtra("info", "Lugar historico de la ciudad de popayan")
                        mediaPlayer?.release() // Liberar el reproductor de medios anterior si existe
                        mediaPlayer = MediaPlayer.create(this@MapsActivity, R.raw.morro)
                        mediaPlayer?.start()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MapsActivity, "Seleccione un lugar primero ee", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                hideAllButtons()
            }
        }
    }

//

    private fun handleLocation(location: LatLng) {
        mMap.clear()
        currentMarker = mMap.addMarker(MarkerOptions().position(location).title("Marker"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f), 2000, null)
    }
}
