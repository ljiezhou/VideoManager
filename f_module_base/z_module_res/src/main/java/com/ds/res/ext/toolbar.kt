package com.ds.res.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BarUtils
import com.gyf.immersionbar.ktx.fitsTitleBarMarginTop
import com.module.res.R
import com.module.res.databinding.BaseIncludeToolbarBinding

/**
 * @Author ljiezhou
 * @date 2023/8/17
 * @Description
 */

fun BaseIncludeToolbarBinding.setDarkToolbar(activity: AppCompatActivity, title: String, isDarkBackIcon: Boolean = false) {
    setToolbar(activity, title = title, isDarkBackIcon = isDarkBackIcon)
}

fun BaseIncludeToolbarBinding.setDarkToolbarNoBack(activity: AppCompatActivity, title: String) {
    setToolbar(activity, showHomeAsUp = false, title = title, isDarkBackIcon = true)
}

fun BaseIncludeToolbarBinding.setDarkToolbarNoBack(activity: Fragment, title: String, showHomeAsUp: Boolean = true) {
    setToolbar(activity.activity as AppCompatActivity, showHomeAsUp = showHomeAsUp, title = title, isDarkBackIcon = true)
}

fun BaseIncludeToolbarBinding.setLightToolbarNoBack(activity: Fragment, title: String, showHomeAsUp: Boolean = true) {
    setToolbar(activity.activity as AppCompatActivity, showHomeAsUp = showHomeAsUp, title = title, isDarkBackIcon = false)
}

fun BaseIncludeToolbarBinding.setLightToolbarNoBack(activity: AppCompatActivity, title: String, showHomeAsUp: Boolean = true) {
    setToolbar(activity, showHomeAsUp = showHomeAsUp, title = title, isDarkBackIcon = false)
}


fun BaseIncludeToolbarBinding.setTitle(title: String) {
    toolbarTitleTv.text = title
}

fun BaseIncludeToolbarBinding.setToolbarPrimary(
    activity: AppCompatActivity,
    title: String,
) {
    setToolbar(activity, title = title, isDarkBackIcon = false, toolbarBg = R.color.common_res_primary_color)
}

@JvmOverloads
fun BaseIncludeToolbarBinding.setToolbar(
    activity: AppCompatActivity,
    showHomeAsUp: Boolean = true,
    title: String,
    isDarkBackIcon: Boolean = true,
    toolbarBg: Int = 0
) {
    activity.setSupportActionBar(toolbarTl)
    activity.supportActionBar?.apply {
        setHomeButtonEnabled(showHomeAsUp)
        setDisplayHomeAsUpEnabled(showHomeAsUp)
        setTitle("")
    }

    if (showHomeAsUp)
        toolbarTl.setNavigationIcon(if (isDarkBackIcon) R.mipmap.base_back_icon_black else R.mipmap.base_back_icon_white)

    toolbarTitleTv.setTextColor(ContextCompat.getColor(activity, if (isDarkBackIcon) R.color.black else R.color.white))
    toolbarTitleTv.text = title

    activity.fitsTitleBarMarginTop(toolbarFl)

    if (toolbarBg != 0) {
        root.setBackgroundResource(toolbarBg)
    } else {
        if (isDarkBackIcon) {
            root.setBackgroundResource(R.color.base_color_white)
        } else {
            root.setBackgroundResource(R.color.base_color_black)

        }
    }
    BarUtils.setStatusBarLightMode(activity, isDarkBackIcon)
}
