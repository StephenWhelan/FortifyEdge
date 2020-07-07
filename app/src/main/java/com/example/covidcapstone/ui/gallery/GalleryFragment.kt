package com.example.covidcapstone.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.covidcapstone.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
class GalleryFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    val UML = LatLng(42.6553, -71.3247)
    val ZOOM_LEVEL = 15f

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map_view.onCreate(savedInstanceState)
        map_view.onResume()

        map_view.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it

        }

        with(map){
            this?.moveCamera(CameraUpdateFactory.newLatLngZoom(UML, ZOOM_LEVEL))
            this?.addMarker(MarkerOptions().position(UML))
        }
    }


}
    /*OnMapReadyCallback {

    val SYDNEY = LatLng(-33.862, 151.21)
    val ZOOM_LEVEL = 13f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)


        val mapFragment : SupportMapFragment? =
            supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    *//**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just move the camera to Sydney and add a marker in Sydney.
     *//*
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return
        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(SYDNEY))
        }
    }
}*/