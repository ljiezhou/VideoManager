package com.ds.commonui.mine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.ds.common.base.ItemLayoutData
import com.ds.commonui.databinding.ScanMineTopLayoutBinding

class MineHeadAdapter : BaseSingleItemAdapter<ItemLayoutData, MineHeadAdapter.VH>() {

    class VH(parent: ViewGroup, val binding: ScanMineTopLayoutBinding = ScanMineTopLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, item: ItemLayoutData?) {

    }


    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}