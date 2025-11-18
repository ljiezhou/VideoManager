package com.ds.permission

import android.content.Context
import com.ds.permission.databinding.ScanPermissionPopupBinding
import com.lxj.xpopup.core.PositionPopupView

/**
 * @Author ljiezhou
 * @date 2024/3/31
 * @Description
 */
class PermissionPopup(context: Context, private val title: String, private val desc: String) : PositionPopupView(context) {
    override fun getImplLayoutId(): Int {
        return R.layout.scan_permission_popup
    }

    override fun onCreate() {
        super.onCreate()
        ScanPermissionPopupBinding.bind(popupImplView).apply {
            titleTv.text = title
            descTv.text = desc
        }
    }
}
