package com.ds.common.compose

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.module.res.R

/**
 * @Author ljiezhou
 * @date 2023/11/23 13:06
 * @Description
 */
@Composable
fun dp8(): Dp {
    return dimensionResource(id = R.dimen.dp_8)
}
@Composable
fun dp9(): Dp {
    return dimensionResource(id = R.dimen.dp_9)
}
@Composable
fun dp10(): Dp {
    return dimensionResource(id = R.dimen.dp_10)
}

@Composable
fun dp12(): Dp {
    return dimensionResource(id = R.dimen.dp_12)
}
@Composable
fun dp14(): Dp {
    return dimensionResource(id = R.dimen.dp_14)
}
@Composable
fun dp16(): Dp {
    return dimensionResource(id = R.dimen.dp_16)
}

@Composable
fun dp18(): Dp {
    return dimensionResource(id = R.dimen.dp_18)
}

@Composable
fun dp20(): Dp {
    return dimensionResource(id = R.dimen.dp_20)
}
@Composable
fun dp32(): Dp {
    return dimensionResource(id = R.dimen.dp_32)
}

@Composable
fun dp(@DimenRes id:Int): Dp {
    return dimensionResource(id = id)
}



@Composable
@ReadOnlyComposable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp