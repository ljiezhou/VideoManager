package com.ds.common.ads

import android.app.Application
import com.qq.e.comm.managers.GDTAdSdk
import com.qq.e.comm.managers.setting.GlobalSetting

/**
 * @Author ljiezhou
 * @date 2023/6/28
 * @Description
 */
val gdtHolder: GDTHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    GDTHolder.INSTANCE
}

class GDTHolder {

    fun init(context: Application, channel: String) {
        GDTAdSdk.init(context, ADSConst.GDT_APP_ID)
        GlobalSetting.setEnableMediationTool(true)
    }

    companion object {
        val INSTANCE: GDTHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            GDTHolder()
        }
    }
}
