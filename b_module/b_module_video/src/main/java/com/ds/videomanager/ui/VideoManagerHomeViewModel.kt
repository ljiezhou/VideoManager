package com.ds.videomanager.ui

import androidx.lifecycle.MutableLiveData
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.videomanager.R

class VideoManagerHomeViewModel : BaseViewModel() {
    val items = MutableLiveData<List<ItemLayoutData>>()

    init {
        items.value = arrayListOf<ItemLayoutData>().apply {
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_video_compress).builder())
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_video_crop).builder())
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_audio_extract).builder())
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_video_manager).builder())
        }
    }
}