package com.ds.common.ads.loadad

import android.app.Activity
import android.widget.FrameLayout
import com.ds.therouter.service.ad.LoadSplashAdListener
import com.dylanc.longan.logDebug
import com.qq.e.ads.splash.SplashAD
import com.qq.e.ads.splash.SplashADListener
import com.qq.e.comm.util.AdError
import java.lang.ref.WeakReference

/**
 * @Author ljiezhou
 * @date 2023/8/30
 * @Description
 */
val loadSplashAd by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { LoadSplashAd() }

class LoadSplashAd {
    private var splashAd: SplashAD? = null
    var loadSplashAdListener: LoadSplashAdListener? = null
    private var container: FrameLayout? = null
    private var weakReference: WeakReference<Activity>? = null
    fun loadAd(activity: Activity, container: FrameLayout) {
        this.weakReference = WeakReference(activity)
        this.container = container
        splashAd = SplashAD(activity, "6056278342621539", mSplashADListener, 0)
        splashAd?.fetchAndShowIn(container)
    }

    fun destory() {
        loadSplashAdListener = null
    }

    private val mSplashADListener = object : SplashADListener {
        override fun onADDismissed() {
            logDebug("onADDismissed")
            loadSplashAdListener?.close()
        }

        override fun onNoAD(p0: AdError?) {
            logDebug("onNoAD${p0?.errorCode} ${p0?.errorMsg}")
            container?.postDelayed({ loadSplashAdListener?.close() }, 1000)
        }

        override fun onADPresent() {
            logDebug("onADPresent")
        }

        override fun onADClicked() {
            logDebug("onADClicked")
            loadSplashAdListener?.onADClicked()
        }

        override fun onADTick(p0: Long) {
            logDebug("onADTick")
            loadSplashAdListener?.onADTick(p0)
        }

        //曝光时调用
        override fun onADExposure() {
            logDebug("onADExposure")
        }

        override fun onADLoaded(p0: Long) {
            logDebug("onADLoaded")
        }
    }
}
