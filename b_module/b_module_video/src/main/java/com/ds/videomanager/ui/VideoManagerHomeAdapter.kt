package com.ds.videomanager.ui

import com.ds.videomanager.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.ds.common.base.ItemLayoutData
import com.ds.videomanager.databinding.VideoManagerHomeItemBinding

class VideoManagerHomeAdapter : BaseQuickAdapter<ItemLayoutData, VideoManagerHomeAdapter.VH>() {
    override fun onBindViewHolder(holder: VH, position: Int, item: ItemLayoutData?) {
        if (item == null) return
        holder.binding.apply {
            titleTv.setText(item.titleId)

            // Set the appropriate icon based on the title ID
            val iconResource = when (item.titleId) {
                R.string.video_manager_video_compress -> R.drawable.ic_video_compress
                R.string.video_manager_video_crop -> R.drawable.ic_video_crop
                R.string.video_manager_audio_extract -> R.drawable.ic_audio_extract
                R.string.video_manager_video_manager -> R.drawable.ic_video_manager
                else -> R.drawable.ic_video_manager
            }
            iconIv.setImageResource(iconResource)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

    class VH(
        val parent: ViewGroup,
        val binding: VideoManagerHomeItemBinding = VideoManagerHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) : RecyclerView.ViewHolder(binding.root)
}