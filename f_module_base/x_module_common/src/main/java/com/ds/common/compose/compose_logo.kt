package com.ds.common.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ds.appname.AppNameConst
import com.ds.res.ResConst
import com.module.res.R

/**
 * @Author ljiezhou
 * @date 2023/12/8 14:06
 * @Description
 */

@Composable
fun Logo() {
    Column(
        modifier = Modifier
            .padding(top = dp(R.dimen.dp_70))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.mipmap.ic_launcher),
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(dp(R.dimen.dp_110))
        )
        Text(
            modifier = Modifier
                .padding(
                    top = dp(R.dimen.dp_40)
                ),
            text = stringResource(AppNameConst.APP_NAME_ID),
            fontSize = fontDimensionResource(R.dimen.sp_24),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .padding(
                    top = dp10()
                ),
            fontWeight = FontWeight.Normal,
            text = stringResource(R.string.res_version_name, ResConst.VERSION_NAME),
            fontSize = fontDimensionResource(R.dimen.sp_20),
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun Preview(){
    Logo()
}