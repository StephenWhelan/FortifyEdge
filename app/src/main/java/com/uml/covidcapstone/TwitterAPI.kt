package com.example.covidcapstone

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.uml.covidcapstone.R

import java.util.*


class  TwitterAPI(
    context: Context,
    al_newslist: ArrayList<String>
) :
    ArrayAdapter<String?>(context, R.layout.adapter_layout, al_newslist as List<String?>) {
    //var context: Context
    var viewHolder: ViewHolder? = null
    var al_newslist = ArrayList<String>()
    override fun getCount(): Int {
        Log.e("ADAPTER LIST SIZE", al_newslist.size.toString() + "")
        return al_newslist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getViewTypeCount(): Int {
        return if (al_newslist.size > 0) {
            al_newslist.size
        } else {
            1
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView =
                LayoutInflater.from(getContext()).inflate(R.layout.adapter_layout, parent, false)
            viewHolder!!.tv_name =
                convertView!!.findViewById<View>(R.id.tv_text) as TextView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder!!.tv_name!!.text = al_newslist[position]
        return convertView!!
    }

    class ViewHolder {
        var tv_name: TextView? = null
    }

    init {
        this.al_newslist = al_newslist
        //this.context = context
    }
}