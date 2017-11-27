package com.jjosft.android.lottovillage.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jjosft.android.lottovillage.R
import com.jjosft.android.lottovillage.model.Model

/**
 * Created by JJSOFT-DESKTOP on 2017-09-04.
 */
class StoreAdapter(private val mProductData: Model.DetailsOfProduct) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //변수 짜야함
        val productTextName: TextView =view.findViewById(R.id.)
        val productTextPrice: TextView=view.findViewById(R.id.)
        val productTextStatus: TextView=view.findViewById(R.id.)
        val productTextContents: TextView=view.findViewById(R.id.)
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

    private fun setProductData(holder: StoreAdapter.ViewHolder,productName: String, productPrice: String,
                               productStatus: String, productContents: String) {
        holder.productTextName.text=productName
        holder.productTextPrice.text=productPrice
        holder.productTextStatus.text=productStatus
        holder.productTextContents.text=productContents
    }

    override fun onClick(view:View?){
        val index:Int=product_list.getChildAdapterPosition(view)-1

        val intent= Intent(applicationContext,Set)
    }
}