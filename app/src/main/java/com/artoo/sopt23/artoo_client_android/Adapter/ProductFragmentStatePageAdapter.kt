package com.artoo.sopt23.artoo_client_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artoo.sopt23.artoo_client_android.Data.*
import com.artoo.sopt23.artoo_client_android.Fragment.*

class ProductFragmentStatePageAdapter(fm : FragmentManager, val fragmentCount : Int,
                                      var allDataList: ArrayList<ProductOverviewData>, var drawingDataList: ArrayList<ProductOverviewData>,
                                      var paintingDataList: ArrayList<ProductOverviewData>, var orientalDataList: ArrayList<ProductOverviewData>,
                                      var mixedDataList: ArrayList<ProductOverviewData>, var craftDataList: ArrayList<ProductOverviewData>,
                                      var photoDataList: ArrayList<ProductOverviewData>): FragmentStatePagerAdapter(fm){

    var productAllFragment: ProductAllFragment = ProductAllFragment().apply{
        allData = allDataList
    }
    var productDrawingFragment: ProductDrawingFragment = ProductDrawingFragment().apply{
        drawingData = drawingDataList
    }
    var productPaintingFragment: ProductPaintingFragment = ProductPaintingFragment().apply{
        paintingData = paintingDataList
    }
    var productOrientalFragment: ProductOrientalFragment = ProductOrientalFragment().apply{
        orientalData = orientalDataList
    }
    var productMixedFragment: ProductMixedFragment = ProductMixedFragment().apply{
        mixedData = mixedDataList
    }
    var productCraftFragment: ProductCraftFragment = ProductCraftFragment().apply{
        craftData = craftDataList
    }
    var productPhotoFragment: ProductPhotoFragment = ProductPhotoFragment().apply{
        photoData = photoDataList
    }



    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return productAllFragment
            1 -> return productDrawingFragment
            2 -> return productPaintingFragment
            3 -> return productOrientalFragment
            4 -> return productMixedFragment
            5 -> return productCraftFragment
            6 -> return productPhotoFragment
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}