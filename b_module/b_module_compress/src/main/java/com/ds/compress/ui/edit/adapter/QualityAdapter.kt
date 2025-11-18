package com.ds.compress.ui.edit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.ds.common.base.ItemLayoutData
import com.ds.compress.R
import com.ds.compress.databinding.ImageEditQualityItemBinding

/**
 * @Author ljiezhou
 * @date 2023/12/8
 * @Description
 */
class QualityAdapter : BaseQuickAdapter<ItemLayoutData, QualityAdapter.VH>() {
    class VH(parent: ViewGroup,
             val binding: ImageEditQualityItemBinding = ImageEditQualityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, position: Int, item: ItemLayoutData?) {
        if (item == null) {
            return
        }
        holder.binding.apply {
            titleTv.text = context.getString(item.titleId)
            descTv.text = context.getString(item.descId)
//                root.setBackgroundResource(if (item.isChecked) R.drawable.image_item_select_bg else R.drawable.image_item_unselect_bg)

            if (item.isChecked) {
                titleTv.setTextColor(ContextCompat.getColor(context, com.module.res.R.color.white))
                descTv.setTextColor(ContextCompat.getColor(context, com.module.res.R.color.white))
                root.setBackgroundResource(R.drawable.image_item_select_bg)
            } else {
                titleTv.setTextColor(ContextCompat.getColor(context, com.module.res.R.color.black))
                descTv.setTextColor(ContextCompat.getColor(context, com.module.res.R.color.black))
                root.setBackgroundResource(R.drawable.image_item_unselect_bg)
            }

        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}