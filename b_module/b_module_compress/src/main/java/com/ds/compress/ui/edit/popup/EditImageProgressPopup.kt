package com.ds.compress.ui.edit.popup

import android.content.Context
import com.ds.compress.R
import com.ds.compress.databinding.ImageEditSaveProgressPopupBinding
import com.lxj.xpopup.core.CenterPopupView

/**
 * @Author ljiezhou
 * @date 2023/12/14
 * @Description
 */
class EditImageProgressPopup(context: Context) : CenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.image_edit_save_progress_popup
    }

    private lateinit var binding: ImageEditSaveProgressPopupBinding
    override fun onCreate() {
        super.onCreate()
        binding = ImageEditSaveProgressPopupBinding.bind(popupImplView).apply {
            descTv.text = context.getString(R.string.image_edit_progress_desc, 1)
        }
    }

    override fun init() {
        super.init()

    }

    fun setCompressDesc(desc: Int) {
        if (this::binding.isInitialized)
            binding.descTv.text = context.getString(R.string.image_edit_progress_desc, desc)
    }
}