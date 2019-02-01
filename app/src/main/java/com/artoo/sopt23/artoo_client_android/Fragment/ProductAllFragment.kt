package com.artoo.sopt23.artoo_client_android.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.artoo.sopt23.artoo_client_android.Activity.ProductUploadActivity
import com.artoo.sopt23.artoo_client_android.Adapter.MypageProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Adapter.ProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.MypageProductData
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.R
import com.artoo.sopt23.artoo_client_android.R.id.ll_mypage_product_count_many
import com.artoo.sopt23.artoo_client_android.R.id.rl_mypage_product_count_zero
import kotlinx.android.synthetic.main.fragment_mypage_product.*
import kotlinx.android.synthetic.main.fragment_mypage_product.view.*

class ProductAllFragment : Fragment() {

    var allData = ArrayList<ProductOverviewData>()
    lateinit var productRecyclerViewAdapter: ProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_all, container, false)
        return view
    }

}