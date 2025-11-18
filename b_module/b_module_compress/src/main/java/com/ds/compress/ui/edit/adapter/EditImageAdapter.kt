package com.ds.compress.ui.edit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.UriUtils
import com.chad.library.adapter4.BaseQuickAdapter
import com.ds.compress.data.ImageItem
import com.ds.compress.databinding.ImageEditImageItemActivityBinding
import com.github.forjrking.image.loadImageOverriveSize
import com.ds.imageload.ImageOptions
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase

/**
 * @Author ljiezhou
 * @date 2023/12/11
 * @Description
 */
class EditImageAdapter : BaseQuickAdapter<ImageItem, EditImageAdapter.VH>() {

    class VH(
        parent: ViewGroup,
        val binding: ImageEditImageItemActivityBinding = ImageEditImageItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, position: Int, item: ImageItem?) {
        if (item == null) {
            return
        }
        holder.binding.imageView.apply {
            displayType = ImageViewTouchBase.DisplayType.FIT_TO_SCREEN
            var canScrool = true
            setDoubleTapListener {
                postDelayed({
                    canScrool = scale == 1f
                    canScrollListener?.canScroll(canScrool)
                }, 210)//缩放动画是200毫秒
            }
            setOnTouchListener { v, event ->
                //如果双击已经判断不滑动了，那这里无论几个触摸点都不在进行判断
                if (canScrool) {
                    canScrollListener?.canScroll(event?.pointerCount!! < 2)
                }
                false
            }

            if (item.compressUri == null) {
//                val size = PhotoMetadataUtils.getBitmapSize(item.uri, ActivityUtils.getTopActivity())
                val size = ImageUtils.getSize(UriUtils.uri2File(item.uri))
                loadImageOverriveSize(item.uri, ImageOptions.OverrideSize(size[0], size[1]))
            } else {
                val bitmap = item.compressUri
                if (bitmap != null)
                    loadImageOverriveSize(bitmap, ImageOptions.OverrideSize(bitmap.width, bitmap.height))
            }
        }
    }

    var canScrollListener: CanScrollListener? = null

    interface CanScrollListener {
        fun canScroll(canScrool: Boolean)
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }


}