package com.ds.commonui.mine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.ds.common.base.ItemLayoutData
import com.ds.commonui.databinding.ScanMineAdContainerBinding
import com.ds.therouter.service.ad.ADService
import com.therouter.TheRouter

class MineAdAdapter : BaseSingleItemAdapter<ItemLayoutData, MineAdAdapter.VH>() {

    class VH(parent: ViewGroup, val binding: ScanMineAdContainerBinding = ScanMineAdContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, item: ItemLayoutData?) {
        holder.binding.container.post {
            TheRouter.get(ADService::class.java)?.apply {
                loadNativeAd(context, holder.binding.container)
            }
        }

    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}
