package com.ds.compress.ui.home

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.ds.common.base.ItemLayoutData
import com.ds.compress.databinding.ImageHomeItemBinding

/**
 * @Author ljiezhou
 * @date 2023/12/5
 * @Description
 */
class HomeAdapter : BaseQuickAdapter<ItemLayoutData, HomeAdapter.VH>() {


    class VH(parent: ViewGroup, val binding: ImageHomeItemBinding = ImageHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)) :
        RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: VH, position: Int, item: ItemLayoutData?) {
        if (item == null) {
            return
        }
        holder.binding.apply {
            titleTv.text = context.getString(item.titleId)
            if (item.descId == 0) {
                descTv.visibility = View.GONE
            } else {
                descTv.visibility = View.VISIBLE
                descTv.text = context.getString(item.descId)
            }
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}