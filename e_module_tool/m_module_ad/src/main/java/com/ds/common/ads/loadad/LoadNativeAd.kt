package com.ds.common.ads.loadad

import android.content.Context
import android.widget.FrameLayout
import com.dylanc.longan.logDebug
import com.qq.e.ads.cfg.VideoOption
import com.qq.e.ads.nativ.ADSize
import com.qq.e.ads.nativ.NativeExpressAD
import com.qq.e.ads.nativ.NativeExpressADView
import com.qq.e.comm.util.AdError


/**
 * @Author ljiezhou
 * @date 2023/8/30
 * @Description
 */
class LoadNativeAd {

    private var mNativeExpressAD: NativeExpressAD? = null

    fun loadAd(context: Context, container: FrameLayout) {
        mNativeExpressAD = NativeExpressAD(context, ADSize(container.width, container.height), "1086072302229670", object : NativeExpressAD.NativeExpressADListener {
            override fun onNoAD(p0: AdError?) {
                logDebug("onNoAD ${p0?.errorCode} ${p0?.errorMsg}")
            }

            override fun onADLoaded(adList: MutableList<NativeExpressADView>?) {
                logDebug("onADLoaded ${adList?.size}}")

                if (adList?.isEmpty() == true) {
                    return
                }
                adList?.get(0)?.apply {
                    render()
                    if (container.childCount > 0) {
                        container.removeAllViews()
                    }
                    container.addView(this)
                }
            }

            override fun onRenderFail(p0: NativeExpressADView?) {
                logDebug("onRenderFail}")
            }

            override fun onRenderSuccess(p0: NativeExpressADView?) {
                logDebug("onRenderSuccess}")
            }

            override fun onADExposure(p0: NativeExpressADView?) {
                logDebug("onADExposure}")
            }

            override fun onADClicked(p0: NativeExpressADView?) {
                logDebug("onADClicked}")
            }

            override fun onADClosed(p0: NativeExpressADView?) {
                logDebug("onADClosed}")
            }

            override fun onADLeftApplication(p0: NativeExpressADView?) {
                logDebug("onADLeftApplication}")
            }
        })
        mNativeExpressAD?.setVideoOption(
            VideoOption.Builder()
                .setAutoPlayPolicy(VideoOption.AutoPlayPolicy.WIFI) // WIFI 环境下可以自动播放视频
                .setAutoPlayMuted(true) // 自动播放时为静音
                .build() //
        )
        mNativeExpressAD?.loadAD(1)
    }
}