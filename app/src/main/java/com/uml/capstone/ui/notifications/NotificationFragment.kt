package com.uml.capstone.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uml.capstone.TwitterAdapter
import com.uml.capstone.R
import java.util.ArrayList


class NotificationFragment: Fragment() {
    private lateinit var notificationViewModel: NotificationViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):

            View? {

        notificationViewModel =
            ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        val root = inflater.inflate(R.layout.activity_notifications, container, false)
        lateinit var obj_adapter: TwitterAdapter
        var al_text = ArrayList<String>()
        al_text.add("test")
        var correctContext = requireContext()
        obj_adapter = TwitterAdapter(correctContext, al_text)
        var adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.region1,
            android.R.layout.simple_list_item_1
        )

        var lv = view?.findViewById(R.id.lv_list) as? ListView
        if (lv != null)
        {
            lv.adapter = obj_adapter
        }
        return root
    }



}





