package com.ds.common.compose.base

import android.app.Activity
import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SizeUtils
import com.ds.common.compose.dp
import com.ds.common.compose.fontDimensionResource


/**
 * @Author ljiezhou
 * @date 2023/11/25 14:25
 * @Description
 */

@Composable
fun topBar(activity: Activity, title: String, bgColor: Color) {
    TopBar(activity, title = title, bgColor = bgColor)
}

@Preview
@Composable
fun previewTopBar() {
    val bgColor = Color.White
    TopBar(null, showBack = false, title = "标题", bgColor = bgColor)
}

//fun getActionBarSize(resources: Resources): Int {
//    val tv = TypedValue()
//
//    // 获取?attr/actionBarSize 的值
//    if (resources.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//        // 将值转换为像素
//        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
//    }
//
//    // 默认值，如果获取失败
//    return 0
//}
@Composable
fun TopBar(fragment: Fragment, showBack: Boolean = true, title: String, isBlack: Boolean = true, bgColor: Color?) {
    TopBar(activity = fragment.activity, showBack = showBack, title = title, isBlack = isBlack, bgColor = bgColor)
}

@Composable
fun TopBar(activity: Activity?, showBack: Boolean = true, title: String, isBlack: Boolean = true, bgColor: Color?) {
    val backIconRes = if (isBlack) com.module.res.R.drawable.baseline_arrow_back_black else com.module.res.R.drawable.baseline_arrow_back_white
    // 获取返回按钮图标
    val backIcon = painterResource(id = backIconRes)
    //状态栏高度
    val barStatusHeight = SizeUtils.px2dp(BarUtils.getStatusBarHeight().toFloat()).dp
    // 获取顶部栏高度
    val topBarHeight = dimensionResource(id = com.module.res.R.dimen.dp_56)

//    val actionBarHeight = TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP,
//        56f, // 默认的 ActionBar 高度，实际项目中应该使用 ?attr/actionBarSize
//        activity?.resources?.displayMetrics
//    ).toInt()

    activity?.let {
        BarUtils.setStatusBarLightMode(it, isBlack)
    }
    val toolBg = bgColor ?: if (isBlack) Color.Black else Color.White

    val textColor = if (isBlack) Color.Black else Color.White
    Column {
        Box(
            modifier = Modifier
                .height(barStatusHeight)
                .fillMaxWidth()
                .background(toolBg)
        )
        Surface(
            modifier = Modifier
                .height(topBarHeight)
                .fillMaxWidth()
        ) {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {},
                navigationIcon = {
                    // 导航按钮图标
                    IconButton(
                        onClick = {
                            activity?.finish()
                        }
                    )
                    {
                        if (showBack) {
                            Image(
                                painter = backIcon,
                                contentDescription = "Back",
                                modifier = Modifier.size(
                                    dp(com.module.res.R.dimen.dp_24)
                                )
                            )
                        }
                    }
                },
                // 禁用默认导航图标
//                backgroundColor = if (isBlack) Color.Black else Color.White
                backgroundColor = toolBg
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = fontDimensionResource(id = com.module.res.R.dimen.sp_18)
                    ),
                    color = textColor
                )
            }
        }
    }
}