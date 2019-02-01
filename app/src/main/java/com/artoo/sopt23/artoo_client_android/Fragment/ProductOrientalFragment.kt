package com.artoo.sopt23.artoo_client_android.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artoo.sopt23.artoo_client_android.Adapter.ProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData

import com.artoo.sopt23.artoo_client_android.R

class ProductOrientalFragment : Fragment() {

    var orientalData = ArrayList<ProductOverviewData>()
    lateinit var productRecyclerViewAdapter: ProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_oriental, container, false)
        return view
    }

}
