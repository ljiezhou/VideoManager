package com.ds.compress.ui.mine

import androidx.lifecycle.MutableLiveData
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.compress.R

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class MineViewModel : BaseViewModel() {

    val items = MutableLiveData<List<ItemLayoutData>>()

    init {
        items.value = arrayListOf(
            ItemLayoutData.Builder().setTitldId(R.string.image_mine_service).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_mine_privatecy).builder()
        )
    }
}