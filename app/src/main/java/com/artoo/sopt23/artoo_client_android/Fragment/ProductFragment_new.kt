package com.artoo.sopt23.artoo_client_android.Fragment
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.TextView
import com.artoo.sopt23.artoo_client_android.Activity.FilterActivity
import com.artoo.sopt23.artoo_client_android.Adapter.ProductFragmentStatePageAdapter
import com.artoo.sopt23.artoo_client_android.Adapter.ProductRecyclerViewAdapter
import com.artoo.sopt23.artoo_client_android.Data.ProductOverviewData
import com.artoo.sopt23.artoo_client_android.Data.Response.Get.GetProductListResponse
import com.artoo.sopt23.artoo_client_android.Network.ApplicationController
import com.artoo.sopt23.artoo_client_android.Network.NetworkService

import com.artoo.sopt23.artoo_client_android.R
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product_new.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivityForResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment_new : Fragment() {

    val jsonObject = JSONObject()
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var allDataList = ArrayList<ProductOverviewData>()
    var drawingDataList = ArrayList<ProductOverviewData>()
    var paintingDataList = ArrayList<ProductOverviewData>()
    var orientalDataList = ArrayList<ProductOverviewData>()
    var mixedDataList = ArrayList<ProductOverviewData>()
    var craftDataList = ArrayList<ProductOverviewData>()
    var photoDataList = ArrayList<ProductOverviewData>()

    lateinit var tv_top_navi_product_all_tab: TextView
    lateinit var tv_top_navi_product_drawing_tab: TextView
    lateinit var tv_top_navi_product_painting_tab: TextView
    lateinit var tv_top_navi_product_oriental_tab: TextView
    lateinit var tv_top_navi_product_mixed_tab: TextView
    lateinit var tv_top_navi_product_craft_tab: TextView
    lateinit var tv_top_navi_product_photo_tab: TextView

    lateinit var inflater: LayoutInflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater

        var view: View = inflater.inflate(R.layout.fragment_product_new, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureTopNavigation()
        //setOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        //getProductAllResponse()
    }

    private fun configureTopNavigation() {
        vp_top_navi_act_frag_pager_product.adapter = ProductFragmentStatePageAdapter(childFragmentManager, 7,
            allDataList, drawingDataList, paintingDataList, orientalDataList, mixedDataList, craftDataList, photoDataList)
        tl_top_navi_act_top_menu_product.setupWithViewPager(vp_top_navi_act_frag_pager_product)

        val topNaviLayout: View = inflater.inflate(R.layout.top_navigation_tab_product, null, false)

        tv_top_navi_product_all_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_all_tab)
        tv_top_navi_product_drawing_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_drawing_tab)
        tv_top_navi_product_painting_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_drawing_tab)
        tv_top_navi_product_oriental_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_oriental_tab)
        tv_top_navi_product_mixed_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_mixed_tab)
        tv_top_navi_product_craft_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_craft_tab)
        tv_top_navi_product_photo_tab = topNaviLayout.findViewById(R.id.tv_top_navi_product_photo_tab)

        tl_top_navi_act_top_menu_product.getTabAt(6)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_photo_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.getTabAt(5)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_craft_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.getTabAt(4)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_mixed_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.getTabAt(3)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_oriental_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_painting_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_drawing_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.btn_top_navi_product_all_tab) as RelativeLayout
        tl_top_navi_act_top_menu_product.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedTab(position = tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedTab(position = tab!!.position)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedTab(position = tab!!.position)
            }
        })
    }

    private fun selectedTab(position: Int) {
        if (position == 0) {
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 1){
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 2) {
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 3){
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 4) {
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else if (position == 5) {
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorEssential))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
        } else {
            tv_top_navi_product_all_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_drawing_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_painting_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_oriental_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_mixed_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_craft_tab.setTextColor(resources.getColor(R.color.colorNonSelectedTab))
            tv_top_navi_product_photo_tab.setTextColor(resources.getColor(R.color.colorEssential))
        }
    }
}