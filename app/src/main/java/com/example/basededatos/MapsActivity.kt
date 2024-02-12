package com.example.basededatos

import android.content.Intent
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
    private lateinit var spinner: Spinner
    private lateinit var senaButton: Button
    private lateinit var morroButton: Button
    private lateinit var parqueButton: Button
    private var currentMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar las vistas después de inflar el diseño
        spinner = binding.spinner
        senaButton = binding.sena1
        morroButton = binding.morro1
        parqueButton = binding.parque

        hideAllButtons()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.parque.setOnClickListener {
            val parqueCaldasLocation = LatLng(2.4419477932001885, -76.6062739)
            handleLocation(parqueCaldasLocation)
        }

        binding.morro1.setOnClickListener {
            val morroLocation = LatLng(2.4834441115192067, -76.56176399432523)
            handleLocation(morroLocation)
        }

        binding.sena1.setOnClickListener {
            val senaLocation = LatLng(2.444727408287769, -76.60014311631677)
            handleLocation(senaLocation)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    1 -> showButtons(binding.sena1)
                    2 -> showButtons(binding.parque)
                    3 -> showButtons(binding.morro1)
                    else -> hideAllButtons()
                }

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
                    hideAllButtons()
                    if (position == 1) {
                        val intent = Intent(this@MapsActivity, LugaresActivity::class.java)
                        intent.putExtra("nombre_lugar", "Sena")
                        intent.putExtra("img", R.drawable.sena)
                        intent.putExtra("latitude", 2.483302497351136)
                        intent.putExtra("longitude", -76.561758126264)
                        intent.putExtra("info", "lugar donde se forman jóvenes para el futuro")
                        startActivity(intent)
                    } else if (position == 2) {
                        val intent = Intent(this@MapsActivity, LugaresActivity::class.java)
                        intent.putExtra("nombre_lugar", "parque caldas")
                        intent.putExtra("img", R.drawable.parque)
                        intent.putExtra("latitude", 2.4419477932001885)
                        intent.putExtra("longitude", -76.6062739)
                        intent.putExtra("info", "Parque central de la ciudad de popayan")
                        startActivity(intent)
                    } else if (position == 3) {
                        val intent = Intent(this@MapsActivity, LugaresActivity::class.java)
                        intent.putExtra("nombre_lugar", "El morro")
                        intent.putExtra("img", R.drawable.morro)
                        intent.putExtra("latitude", 2.444638282857905)
                        intent.putExtra("longitude", -76.60015846325189)
                        intent.putExtra("info", "Lugar historico de la ciudad de popayan")
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MapsActivity, "Seleccione un lugar primero", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                hideAllButtons()
            }
        }
    }

    private fun showButtons(buttonToShow: Button) {
        senaButton.visibility = if (buttonToShow == senaButton) View.VISIBLE else View.INVISIBLE
        parqueButton.visibility = if (buttonToShow == parqueButton) View.VISIBLE else View.INVISIBLE
        morroButton.visibility = if (buttonToShow == morroButton) View.VISIBLE else View.INVISIBLE
    }

    private fun hideAllButtons() {
        senaButton.visibility = View.INVISIBLE
        parqueButton.visibility = View.INVISIBLE
        morroButton.visibility = View.INVISIBLE
    }

    private fun handleLocation(location: LatLng) {
        mMap.clear()
        currentMarker = mMap.addMarker(MarkerOptions().position(location).title("Marker"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f), 2000, null)
    }
}
