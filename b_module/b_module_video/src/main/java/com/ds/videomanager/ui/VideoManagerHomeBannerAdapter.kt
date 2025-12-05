package com.ds.videomanager.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.ds.videomanager.databinding.VideoManagerHomeBannerBinding

class VideoManagerHomeBannerAdapter : BaseSingleItemAdapter<Any, VideoManagerHomeBannerAdapter.VH>() {
    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, item: Any?) {

    }
//
//    override fun onBindViewHolder(holder: VH, position: Int, item: Any?) {
//        if (item == null) return
//        holder.binding.apply {
//
//        }
//    }


    class VH(
        val parent: ViewGroup,
        val binding: VideoManagerHomeBannerBinding = VideoManagerHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) : RecyclerView.ViewHolder(binding.root)
}