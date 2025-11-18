package com.ds.compress.ui.param.popup

import android.content.Context
import com.blankj.utilcode.util.ScreenUtils
import com.ds.compress.R
import com.ds.compress.databinding.CompressProgressPopupBinding
import com.lxj.xpopup.core.CenterPopupView

/**
 * @Author ljiezhou
 * @date 2024/10/29
 * @Description
 */
class CompressPopup(context: Context) : CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.compress_progress_popup
    }

    private lateinit var binding: CompressProgressPopupBinding
    override fun onCreate() {
        super.onCreate()
        binding = CompressProgressPopupBinding.bind(popupImplView).apply {
            compressingProgressPb.progress = 0
            compressingProgressTv.text = "0%"
        }
    }

    fun setProgress(progress: Int) {
        if (this::binding.isInitialized) {
            binding.compressingProgressPb.progress = progress
            binding.compressingProgressTv.text = "$progress%"
        }
    }

    override fun getMaxWidth(): Int {
        return (ScreenUtils.getScreenWidth() * 0.7).toInt()
    }
}