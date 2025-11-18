package com.ds.permission

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ThreadUtils
import com.ds.res.ResConst
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation

object PermissionUtils {
    //    private var weakReference: WeakReference<Activity>? = null
    private var doNotAskAgainStr: String = ""

    private var permissionPopup: BasePopupView? = null

    fun init(activity: Activity) {
//        weakReference = WeakReference(activity)
    }

    /**
     * 权限申请流程
     * 1.如果直接申请相机或者相册权限，则需要单独申请
     * 2.如果先申请了文件管理权限，则不用再申请相册权限
     * >=30 MANAGE_EXTERNAL_STORAGE
     */
//    val sotragePermiss by lazy {
//        arrayListOf(
//            Permission.MANAGE_EXTERNAL_STORAGE
//        )
//    }

    private val imagePermiss by lazy {
        arrayListOf(Permission.READ_MEDIA_IMAGES)
    }


    /**
     * 申请相机权限
     */
    fun cameraPermission(activity: Activity, block: () -> Unit) {
//        if (weakReference?.get() == null) {
//            return
//        }
        XXPermissions.with(activity)
            .permission(Permission.CAMERA)
            .request(object : OnPermissionCallback {
                override fun onGranted(p0: MutableList<String>, p1: Boolean) {
                    if (p1) {
                        block.invoke()
                    }
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    super.onDenied(permissions, doNotAskAgain)
                    this@PermissionUtils.doNotAskAgainStr = activity.getString(R.string.scan_do_not_ask_again_desc2, ResConst.APP_NAME)
                    if (doNotAskAgain) {
                        startPermissionActivity(activity, permissions)
                    }
                }
            })
    }

    /**
     * 申请图片权限提示
     */
    fun imagePermission(activity: Activity, title: String, desc: String, block: () -> Unit) {
        if (XXPermissions.isGranted(activity, imagePermiss)) {
            block.invoke()
        } else {
            permissionPopup = XPopup.Builder(activity)
                .popupAnimation(PopupAnimation.TranslateFromTop)
                .hasBlurBg(false)
                .asCustom(PermissionPopup(activity, title, desc))
                .show()
            doNotAskAgainStr = StringUtils.getString(R.string.scan_do_not_ask_again_desc2, ResConst.APP_NAME, ResConst.APP_NAME)
            storagePermission(activity, imagePermiss) {
                permissionPopup?.dismiss()
                ThreadUtils.runOnUiThreadDelayed({
                    block.invoke()
                }, 350)
//                permissionPopup?.postDelayed({
//                    block.invoke()
//                }, 300)
//                mView.postDelayed({  }, 300)
            }
        }
    }

    /**
     * 存储权限提示
     */
    fun storagePermissionTips(mActivity: Activity, title: String, desc: String, block: () -> Unit) {
//        if (!XXPermissions.isGranted(mActivity, sotragePermiss)) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {//Android 11 以上需要
//                val manageStoragePopup = ManageStoragePopup(mActivity, object : ManageStoragePopup.ManageStorageListener {
//                    override fun onClick() {
//                        storagePermission(mActivity, sotragePermiss) {
//                            block.invoke()
//                        }
//                    }
//                })
//                XPopup.Builder(mActivity)
//                    .hasBlurBg(false)
//                    .asCustom(manageStoragePopup)
//                    .show()
//
//            } else {//
//                permissionPopup = XPopup.Builder(mActivity)
//                    .popupAnimation(PopupAnimation.TranslateFromTop)
//                    .hasBlurBg(false)
//                    .asCustom(PermissionPopup(mActivity, title, desc))
//                    .show()
//                doNotAskAgainStr = mActivity.getString(R.string.scan_do_not_ask_again_desc, ResConst.APP_NAME, ResConst.APP_NAME)
//                storagePermission(mActivity, sotragePermiss) {
//                    permissionPopup?.dismiss()
//                    ThreadUtils.runOnUiThreadDelayed({
//                        block.invoke()
//                    }, 350)
////                    mView.postDelayed({ block.invoke() }, 300)
//                }
//            }
//        } else {
//            block.invoke()
//        }
    }

    /**
     * 申请存储权限
     */
    fun storagePermission(activity: Activity, permiss: ArrayList<String>, block: () -> Unit) {

        XXPermissions.with(activity)
            .permission(permiss)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (allGranted) {
                        block.invoke()
                    }
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    permissionPopup?.dismiss()
                    permissions.forEach {
                        LogUtils.d(permiss)
                    }
                    LogUtils.d("onDenied")
                    if (doNotAskAgain) {
                        startPermissionActivity(activity, permissions)
                    } else {
                        LogUtils.d("获取权限失败")
                    }
                }
            })
    }


    private fun startPermissionActivity(activity: Activity, permissions: MutableList<String>) {

        AlertDialog.Builder(activity).apply {
            setIcon(com.module.res.R.mipmap.ic_launcher)
            setMessage(doNotAskAgainStr)
            setPositiveButton(activity.getString(R.string.scan_do_not_ask_again_sure)) { p0, p1 ->
                XXPermissions.startPermissionActivity(
                    activity,
                    permissions
                )
            }
            setNegativeButton(R.string.scan_do_not_ask_again_cancel) { p0, p1 -> }
            show()
        }
    }
}