package com.ds.videomanager.ui

import android.os.Bundle
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.videomanager.databinding.VideoManagerHomeFragmentBinding

class VideoManagerHomeFragment: BaseVbFragment<VideoManagerHomeFragmentBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
    }


    companion object{
        fun getInstance():VideoManagerHomeFragment{
            return VideoManagerHomeFragment()
        }
    }
}