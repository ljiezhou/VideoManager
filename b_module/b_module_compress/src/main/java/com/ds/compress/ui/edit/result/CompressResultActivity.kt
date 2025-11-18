package com.ds.compress.ui.edit.result

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.PathUtils
import com.ds.common.base.activity.BaseComposeActivity
import com.ds.common.compose.base.TopBar
import com.ds.common.compose.dp
import com.ds.common.compose.dp10
import com.ds.common.compose.fontDimensionResource
import com.ds.res.ResConst
import com.dylanc.longan.startActivity
import com.module.res.R
import java.io.File

/**
 * @Author ljiezhou
 * @date 2023/12/16
 * @Description
 */
class CompressResultActivity : BaseComposeActivity<CompressResultViewModel>() {
    private var mActivity: Activity? = CompressResultActivity@ this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarLightMode(this, true)
        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                mActivity,
                title = "",
                bgColor = Color.White
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = dp(R.dimen.dp_60),
                        end = dp(R.dimen.dp_60)
                    )
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ResultDesc()
                Spacer(modifier = Modifier.size(dp(R.dimen.dp_30)))
                BtnBox(isDelete = false)
                Spacer(modifier = Modifier.size(dp(R.dimen.dp_15)))
                BtnBox(isDelete = true)
            }
        }
    }

    @Composable
    fun ResultDesc() {
        val savePath = if (isPreview)
            "/storage/emulated/0/DCIM" + File.separator + ResConst.APP_NAME
        else {
            PathUtils.getExternalDcimPath() + File.separator + ResConst.APP_NAME
        }
        Image(
            painter = painterResource(com.ds.compress.R.drawable.image_compress_result_img),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    top = dp(R.dimen.dp_40)
                )
                .clip(RoundedCornerShape(16.dp))
                .size(dp(R.dimen.dp_90))
        )
        Text(
            modifier = Modifier.padding(
                top = dp(id = R.dimen.dp_20),
            ),
            text = stringResource(
                id = com.ds.compress.R.string.image_compress_result_title,
                ResConst.APP_NAME
            ),
            fontSize = fontDimensionResource(R.dimen.sp_20),
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(
                top = dp(id = R.dimen.dp_5),
            ),
            textAlign = TextAlign.Center,
            text = stringResource(id = com.ds.compress.R.string.image_compress_result_desc) + savePath,
            fontSize = fontDimensionResource(R.dimen.sp_16),
            fontWeight = FontWeight.Normal,
            color = Color(0xCC000000)//0xFF999999
        )
    }

    @Composable
    fun BtnBox(isDelete: Boolean) {
        FilledTonalButton(
            onClick = {
                if (isDelete) {
                    finish()
                } else {

                }
            },
            shape = RoundedCornerShape(dp10()),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isDelete) colorResource(id = R.color.common_res_primary_color) else Color(0x1A000000)
            ),
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(dp(R.dimen.dp_16))
        ) {
            Text(
                text = stringResource(id = if (isDelete) com.ds.compress.R.string.image_compress_result_back else com.ds.compress.R.string.image_compress_result_del),
                textAlign = TextAlign.Center,
                color = if (isDelete) Color.White else Color.Black
            )
        }
    }

    companion object {
        fun action() = startActivity<CompressResultActivity>()
    }

    private var isPreview = false

    @Preview
    @Composable
    fun previewResult() {
        isPreview = true
        mActivity = null
        Content()
    }
}