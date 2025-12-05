package com.ds.videomanager.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.BarUtils
import com.chad.library.adapter4.QuickAdapterHelper
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.videomanager.R
import com.ds.videomanager.databinding.VideoManagerHomeFragmentBinding
import kotlin.getValue

class VideoManagerHomeFragment : BaseVbFragment<VideoManagerHomeFragmentBinding>() {
    private val mViewModel by viewModels<VideoManagerHomeViewModel>()
    private val mAdapter = VideoManagerHomeAdapter()
    private val helper by lazy {
        QuickAdapterHelper.Builder(mAdapter).build().addBeforeAdapter(VideoManagerHomeBannerAdapter())
    }

    override fun initView(savedInstanceState: Bundle?) {
        ConstraintSet().apply {
            clone(mViewBind.root)
            // Set the height of the top space to status bar height so other views positioned
            // below it are pushed down. Using constrainHeight works reliably for a Space view
            // where height is 0dp in XML.
//            constrainHeight(mViewBind.topSpace.id, BarUtils.getStatusBarHeight())
            setMargin(
                mViewBind.topSpace.id,
                ConstraintSet.TOP,
                BarUtils.getStatusBarHeight()
            )
            applyTo(mViewBind.root)
        }

        val spanCount = 2
        val gridLayoutManager = GridLayoutManager(requireContext(), spanCount).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) spanCount else 1
                }
            }
        }
        mAdapter.submitList(arrayListOf<ItemLayoutData>().apply {
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_video_compress).builder())
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_video_crop).builder())
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_audio_extract).builder())
            add(ItemLayoutData.Builder().setTitldId(R.string.video_manager_video_manager).builder())
        })
        mViewBind.funRv.layoutManager = gridLayoutManager
        mViewBind.funRv.adapter = helper.adapter
    }

    override fun createObserver() {
        mViewModel.items.observe(this) {
            mAdapter.submitList(it)
        }
    }

    override fun initListener() {
        super.initListener()

        // Set click listener for the video compression banner
//        mViewBind.videoCompressBanner.setOnClickListener {
//            // Find the video compression item in the adapter
//            val compressionItem = mViewModel.items.value?.find {
//                it.titleId == R.string.video_manager_video_compress
//            }
//
//            // Handle the banner click - can implement direct navigation to the video compression feature
//            compressionItem?.let { item ->
//                // Navigate to the video compression feature
//                // You can implement the navigation logic here
//                // For example, if you have a navigation action defined in your Navigation Component:
//                // findNavController().navigate(R.id.action_homeFragment_to_videoCompressFragment)
//
//                // Or handle the item the same way as the adapter would
//                // by finding its position first
//                val position = mViewModel.items.value?.indexOf(item) ?: -1
//                if (position != -1) {
//                    // Call the adapter's click listener directly with the item
//                    mAdapter.getItem(position)?.let { clickedItem ->
//                        // Process the click on the compression item
//                    }
//                }
//            }
//        }

        mAdapter.setOnItemClickListener { adapter, view, position ->
            // Get the item that was clicked
//            val item = adapter.getItem(position)
            // Here you can handle the click event for the item
            // For example, show a toast or navigate to another screen
//            item?.let {
            // The view is already using the ripple effect defined in the XML
            // You can add additional functionality here based on your requirements
//            }
        }
    }


    companion object {
        fun getInstance(): VideoManagerHomeFragment {
            return VideoManagerHomeFragment()
        }
    }
}