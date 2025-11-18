package com.ds.permission

import android.content.Context
import com.ds.permission.databinding.ScanManageStoragePopupBinding
import com.lxj.xpopup.core.BottomPopupView

/**
 * @Author ljiezhou
 * @date 2024/3/31
 * @Description
 */
class ManageStoragePopup(context: Context, private val manageStorageListener: ManageStorageListener) : BottomPopupView(context) {
    override fun getImplLayoutId(): Int {
        return R.layout.scan_manage_storage_popup
    }

    override fun onCreate() {
        super.onCreate()
        ScanManageStoragePopupBinding.bind(popupImplView).apply {
            sureTv.setOnClickListener {
                manageStorageListener.onClick()
                postDelayed({
                    dismiss()
                }, 300)
            }
            cancelTv.setOnClickListener {
                dismiss()
            }
        }
    }

    interface ManageStorageListener {
        fun onClick()
    }
}
