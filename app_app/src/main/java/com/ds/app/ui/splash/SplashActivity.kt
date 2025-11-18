package com.ds.app.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
//import com.ds.common.ads.loadad.LoadSplashAd
//import com.ds.common.ads.loadad.LoadSplashAdListener
import com.ds.app.holder.initHolder
import com.ds.app.ui.main.MainActivity
import com.ds.common.base.activity.BaseVbActivity
import com.lxj.xpopup.XPopup
import com.ds.common.datastore.SettingsRepository
import com.ds.app.databinding.AppSplashActivityBinding
import com.ds.therouter.service.ad.ADService
import com.ds.therouter.service.ad.LoadSplashAdListener
import com.therouter.TheRouter

/**
 * @Author ljiezhou
 * @date 2023/6/29
 * @Description
 */
class SplashActivity : BaseVbActivity<AppSplashActivityBinding>() {
    private val adService by lazy { TheRouter.get(ADService::class.java) }
    private val mViewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createObserver() {
        SettingsRepository.isFirst.asLiveData().observe(this) {
            LogUtils.d("isfirst$it")
//            if (it == true) {
//                mViewBind.splashLogoLl.visibility = View.VISIBLE
//                showDialog()
//            } else {
//                initHolder.init {
//                    loadSplashAD()
//                }
//            }
            initHolder.init {
                loadSplashAD()
            }
        }
    }

    override fun initListener() {
    }

    private var canJump = false
    private fun loadSplashAD() {
        mViewBind.root.post {
            LogUtils.d("adService ${adService == null}")
            if (adService == null) {
                next()
            } else {
                adService!!.loadSplashAd(this, mViewBind.adContainer, object : LoadSplashAdListener {
                    override fun onADExposure() {
                        mViewBind.skipTv.visibility = View.VISIBLE
                    }

                    override fun onADTick(millisUntilFinished: Long) {
                        mViewBind.skipTv.text = millisUntilFinished.toString()
                    }

                    override fun onADClicked() {
                    }

                    override fun close() {
                        next()
                    }
                }
                )
            }
        }
    }

    private fun next() {
        if (canJump) {
            MainActivity.action(this)
            this.finish()
        } else {
            canJump = true
        }
    }

    override fun onPause() {
        super.onPause()
        canJump = false
    }

    override fun onResume() {
        super.onResume()
        if (canJump) {
            next()
        }
        canJump = true
    }

    private fun showDialog() {
        XPopup.Builder(this@SplashActivity)
            .isDestroyOnDismiss(true).dismissOnBackPressed(false).dismissOnTouchOutside(false).asCustom(SplashDialog(this@SplashActivity)).show()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}