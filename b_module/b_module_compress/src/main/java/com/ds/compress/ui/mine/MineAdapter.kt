package com.ds.compress.ui.mine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.ds.common.base.ItemLayoutData
import com.ds.compress.databinding.ImageMineItemBinding

/**
 * @Author ljiezhou
 * @date 2023/12/5
 * @Description
 */
class MineAdapter : BaseQuickAdapter<ItemLayoutData, MineAdapter.VH>() {


    class VH(parent: ViewGroup, val binding: ImageMineItemBinding = ImageMineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, position: Int, item: ItemLayoutData?) {
        if (item == null) {
            return
        }
        holder.binding.apply {
            titleIv.text = context.getString(item.titleId)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }
}