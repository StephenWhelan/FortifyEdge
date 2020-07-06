package com.example.covidcapstone.ui.health_tips

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covidcapstone.R
import kotlinx.android.synthetic.main.fragment_healthtips.*

class HealthTipsFragment : Fragment() {

    private lateinit var healthTipsViewModel: HealthTipsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        healthTipsViewModel =
                ViewModelProviders.of(this).get(HealthTipsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_healthtips, container, false)
        // get reference to button
        val btnCDC = root.findViewById(R.id.buttonCDC) as Button
// set on-click listener
        btnCDC.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/index.html")
            startActivity(openURL)
        }




        return root
    }

}

