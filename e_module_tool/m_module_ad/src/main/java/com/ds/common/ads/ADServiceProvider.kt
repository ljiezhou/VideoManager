package com.ds.common.ads

import android.app.Application
import android.content.Context
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.ds.common.ads.loadad.LoadNativeAd
import com.ds.common.ads.loadad.LoadSplashAd
import com.ds.therouter.service.ad.ADService
import com.ds.therouter.service.ad.LoadSplashAdListener
import com.therouter.inject.ServiceProvider
import com.ds.common.ads.loadad.loadSplashAd

/**
 * @Author ljiezhou
 * @date 2024/6/17
 * @Description
 */

@ServiceProvider
fun ad(): ADService {
    return object : ADService {
        override fun init(application: Application, channel: String) {
            gdtHolder.init(application, channel)
        }

        override fun loadSplashAd(activity: AppCompatActivity, container: FrameLayout, loadSplashAdListener: LoadSplashAdListener) {
//            LoadSplashAd().apply {
//                loadSplashAdListener = loadSplashAdListener
//                loadAd(activity, container)
//            }
            loadSplashAd.loadSplashAdListener = loadSplashAdListener
            loadSplashAd.loadAd(activity, container)
        }

        override fun loadNativeAd(context: Context, container: FrameLayout) {
            LoadNativeAd().loadAd(context, container)
        }


    }
}