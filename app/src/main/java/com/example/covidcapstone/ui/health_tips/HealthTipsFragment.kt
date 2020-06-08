package com.example.covidcapstone.ui.health_tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covidcapstone.R

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
        val textView: TextView? = root.findViewById(R.id.text_gallery)
        healthTipsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView?.text = it
        })
        return root
    }
}