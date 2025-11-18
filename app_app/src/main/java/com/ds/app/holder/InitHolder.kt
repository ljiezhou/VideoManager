package com.ds.app.holder

import com.blankj.utilcode.util.LogUtils
import com.ds.common.ext.appContext
import com.ds.common.util.ChannelUtil
import com.ds.therouter.service.BuglyService
import com.ds.therouter.service.UmengService
import com.ds.therouter.service.ad.ADService
import com.therouter.TheRouter

/**
 * @Author ljiezhou
 * @date 2023/8/3
 * @Description
 */

val initHolder: InitHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    InitHolder()
}

class InitHolder {
    fun init(block: () -> Unit) {
        TheRouter.get(UmengService::class.java)?.apply {
            LogUtils.d("UmengService")
            init(appContext, ChannelUtil.channel)
        }
        TheRouter.get(BuglyService::class.java)?.apply {
            LogUtils.d("BuglyService")
            init(appContext, ChannelUtil.channel)
        }
        TheRouter.get(ADService::class.java)?.apply {
            LogUtils.d("ADService")
            init(appContext, ChannelUtil.channel)
        }
        block.invoke()
    }
}