package com.uml.capstone.ui.health_tips

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uml.capstone.R

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
        val mCDC = root.findViewById(R.id.maskButton) as Button
        val tCDC = root.findViewById(R.id.testButton) as Button
        val wCDC = root.findViewById(R.id.handButton) as Button
        val pCDC = root.findViewById(R.id.protectButton) as Button

// set on-click listener
        mCDC.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/diy-cloth-face-coverings.html")
            startActivity(openURL)
        }
        tCDC.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/testing.html")
            startActivity(openURL)
        }
        wCDC.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.cdc.gov/handwashing/index.html")
            startActivity(openURL)
        }
        pCDC.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention.html")
            startActivity(openURL)
        }






        return root
    }

}

