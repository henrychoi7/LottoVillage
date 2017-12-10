package com.jjosft.android.lottovillage.adapters

import android.app.Activity
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjosft.android.lottovillage.R

/**
 * Created by JJSOFT-DESKTOP on 2017-09-04.
 */
class StoreAdapter(private val mActivity: Activity) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreAdapter.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 3
    }
}