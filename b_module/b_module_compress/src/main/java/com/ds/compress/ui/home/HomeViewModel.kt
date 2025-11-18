package com.ds.compress.ui.home

import androidx.lifecycle.MutableLiveData
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.compress.R
import com.ds.res.ResConst

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class HomeViewModel : BaseViewModel() {

    val items = MutableLiveData<List<ItemLayoutData>>()

    init {
        val tmpItems = arrayListOf(
            ItemLayoutData.Builder().setTitldId(R.string.compress_home_compress_size).setDescId(R.string.compress_home_compress_size_description).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.compress_home_compress_dimension).setDescId(R.string.compress_home_compress_dimension_description).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.compress_home_history).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_compress_by_quality_title).setDescId(R.string.image_compress_by_quality_desc).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_compress_by_scale_title).setDescId(R.string.image_compress_by_scale_desc).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_compress_by_about_us).builder(),
//                ItemLayoutData.Builder().setTitldId(R.string.image_compress_by_contact_us).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_home_test).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_compress_more).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_compress_by_fix_size_title).setDescId(R.string.image_compress_by_fix_size_desc).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_compress_by_quality_title).setDescId(R.string.image_compress_by_quality_desc).builder(),
        )
        if (ResConst.IS_DEBUG)
            tmpItems.add(ItemLayoutData.Builder().setTitldId(R.string.image_home_test).builder())

        items.value = tmpItems
    }
}