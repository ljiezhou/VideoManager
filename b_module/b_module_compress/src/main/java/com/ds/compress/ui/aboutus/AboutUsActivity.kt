package com.ds.compress.ui.aboutus

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blankj.utilcode.util.StringUtils
import com.ds.common.base.activity.BaseComposeActivity
import com.ds.common.compose.Logo
import com.ds.common.compose.base.TopBar
import com.ds.common.compose.dp
import com.ds.common.compose.fontDimensionResource
import com.ds.common.therouter.commonui.CommonUIManager

import com.ds.compress.R
import com.dylanc.longan.startActivity

/**
 * @Author ljiezhou
 * @date 2023/12/16
 * @Description
 */
class AboutUsActivity : BaseComposeActivity<AboutUsViewModel>() {
    private var mActivity: Activity? = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            content()
        }
    }

    @Composable
    fun content() {
        val bgColor = Color.White
        Column(
            modifier = Modifier
                .background(bgColor)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                mActivity,
                title = stringResource(id = R.string.image_compress_by_about_us),
                bgColor = bgColor
            )
            Logo()
            Spacer(modifier = Modifier.size(dp(com.module.res.R.dimen.dp_60)))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                urlText(title = com.module.res.R.string.res_privacy_policy)
                Spacer(modifier = Modifier.size(dp(com.module.res.R.dimen.dp_20)))
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(dp(com.module.res.R.dimen.dp_15))
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.size(dp(com.module.res.R.dimen.dp_20)))
                urlText(title = com.module.res.R.string.res_terms_of_service)
            }
        }
    }

    @Composable
    fun urlText(title: Int) {
        Text(
            modifier = Modifier
                .padding(dp(com.module.res.R.dimen.dp_5))
                .clickable {
                    CommonUIManager.privacy(StringUtils.getString(title))
                },
            fontWeight = FontWeight.Normal,
            text = stringResource(id = title),
            fontSize = fontDimensionResource(com.module.res.R.dimen.sp_18),
            color = Color.Black,
            textDecoration = TextDecoration.Underline
        )
    }

    companion object {
        fun action() = startActivity<AboutUsActivity>()
    }


    private var isPreview = false

    @Preview
    @Composable
    fun previewAboutUs() {
        isPreview = true
        mActivity = null
        content()
    }
}
