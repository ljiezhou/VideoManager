package com.ds.commonui.mine

import androidx.lifecycle.MutableLiveData
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.res.ResConst

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class MineViewModel : BaseViewModel() {

    val items = MutableLiveData<List<ItemLayoutData>>()


    init {
        items.value = arrayListOf(
            ItemLayoutData.Builder().setTitldId(com.module.res.R.string.res_terms_of_service).setIcon(com.module.res.R.drawable.service_img).builder(),
            ItemLayoutData.Builder().setTitldId(com.module.res.R.string.res_privacy_policy).setIcon(com.module.res.R.drawable.privacy_img).builder(),
//            ItemLayoutData.Builder().setTitldId(com.module.res.R.string.food_history_title).setIcon(com.module.res.R.drawable.history_img).builder(),
            ItemLayoutData.Builder().setTitldId(com.module.res.R.string.feedback).setIcon(com.module.res.R.drawable.face_back_img).builder(),
        )
    }
}