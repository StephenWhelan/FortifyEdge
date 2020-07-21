package com.uml.capstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uml.capstone.R
import android.content.Intent
import android.net.Uri
import android.widget.Button


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val btnCheck = root.findViewById(R.id.btnCheck) as Button
        val btnPos = root.findViewById(R.id.btnPos) as Button

        btnCheck.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://umasslowell.co1.qualtrics.com/jfe/form/SV_6htgVDNg11U7VUp")
            startActivity(openURL)
        }

        btnPos.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://umasslowell.co1.qualtrics.com/jfe/form/SV_bO6VvHBOppvNhgV")
            startActivity(openURL)
        }

        return root
    }
}