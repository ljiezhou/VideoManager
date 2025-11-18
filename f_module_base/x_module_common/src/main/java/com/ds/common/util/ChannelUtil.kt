package com.ds.common.util

import android.app.Application
import android.text.TextUtils
import android.util.Log
import com.tencent.vasdolly.helper.ChannelReaderUtil

/**
 * @Author ljiezhou
 * @date 2023/6/28
 * @Description
 */
class ChannelUtil {

    companion object {
        private const val TAG = "ChannelUtil"
        const val channel = ""
        fun channel(application: Application): String {
            //获取当前渠道
            var channel = ChannelReaderUtil.getChannel(application)
//        var channel = HumeSDK.getChannel(this)
//        if (TextUtils.isEmpty(channel)) channel = ChannelReaderUtil.getChannel(this.applicationContext)
//        if (TextUtils.isEmpty(channel)) channel = WalleChannelReader.getChannel(this.applicationContext)
            if (TextUtils.isEmpty(channel)) channel = "ds-base"
//        SystemUtil.getInstance().channel = channel
            Log.d(TAG, "channel: $channel")
//        LogcatUtil.d("channel: $channel")
            channel = channel
            return channel
        }

    }
}