package com.ds.videomanager.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.videomanager.databinding.VideoManagerHomeFragmentBinding
import kotlin.getValue

class VideoManagerHomeFragment : BaseVbFragment<VideoManagerHomeFragmentBinding>() {
    private val mViewModel by viewModels<VideoManagerHomeViewModel>()
    private val mAdapter = VideoManagerHomeAdapter()

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.funRv.addItemDecoration(object : RecyclerView.ItemDecoration() {

        })
        mViewBind.funRv.adapter = mAdapter
    }

    override fun createObserver() {
        mViewModel.items.observe(this) {
            mAdapter.submitList(it)
        }
    }

    override fun initListener() {
        super.initListener()
        mAdapter.setOnItemClickListener { adapter, view, position ->
            // Get the item that was clicked
            val item = adapter.getItem(position)
            // Here you can handle the click event for the item
            // For example, show a toast or navigate to another screen
            item?.let {
                // The view is already using the ripple effect defined in the XML
                // You can add additional functionality here based on your requirements
            }
        }
    }


    companion object {
        fun getInstance(): VideoManagerHomeFragment {
            return VideoManagerHomeFragment()
        }
    }
}