package com.ds.compress.ui.edit.popup

import android.content.Context
import com.blankj.utilcode.util.ScreenUtils
import com.ds.compress.R
import com.ds.compress.databinding.ImageEditCustomSizePopupBinding
import com.lxj.xpopup.core.CenterPopupView

/**
 * @Author ljiezhou
 * @date 2023/12/8
 * @Description
 */
class CustomSizePopup(context: Context) : CenterPopupView(context) {
    override fun getImplLayoutId(): Int {
        return R.layout.image_edit_custom_size_popup
    }

    private lateinit var imageEditCustomSizePopupBinding: ImageEditCustomSizePopupBinding
    override fun init() {
        super.init()
        imageEditCustomSizePopupBinding = ImageEditCustomSizePopupBinding.bind(popupImplView).apply {
            customSizeCancelTv.setOnClickListener { dismiss() }
            customSizeSureTv.setOnClickListener {
                customSizeListener?.customSize(fixWidthEt.text.toString().toInt(), fixHeightEt.text.toString().toInt())
                dismiss()
            }
        }
    }

    override fun getMaxWidth(): Int {
        return (ScreenUtils.getScreenWidth() * 0.9).toInt()
    }

    var customSizeListener: CustomSizeListener? = null

    interface CustomSizeListener {
        fun customSize(width: Int, height: Int)
    }
}