package com.ds.commonui.web

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ds.common.CommonConst
import com.ds.common.base.activity.BaseVbActivity
import com.ds.common.therouter.Path
import com.ds.commonui.databinding.CommonUiPrivacyActivityBinding
import com.ds.res.ResConst
import com.ds.res.ext.setToolbar
import com.dylanc.longan.safeIntentExtras
import com.dylanc.longan.startActivity
import com.dylanc.longan.topActivity
import com.just.agentweb.AgentWeb
import com.module.res.R
import com.therouter.router.Route

/**
 * @Author ljiezhou
 * @date 2023/8/1
 * @Description
 */
@Route(path = Path.App.PRIVACYACTIVITY)
class PrivacyActivity : BaseVbActivity<CommonUiPrivacyActivityBinding>() {
    private val mTitle: String by safeIntentExtras(CommonConst.TITLE)
    private val mUrl: String by safeIntentExtras(CommonConst.URL)

    private var mAgentWeb: AgentWeb? = null

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.toolbar.setToolbar(this, title = mTitle)
        val param = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mAgentWeb = AgentWeb.with(this)//
            .setAgentWebParent(mViewBind.container, -1, param)//传入AgentWeb的父控件。
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(mUrl) //WebView载入该url地址的页面并显示。
    }

    override fun createObserver() {}
    override fun initListener() {

    }

    companion object {
//        private fun action(title: String, url: String) = startActivity<PrivacyActivity>(TITLE to title, URL to url)
//
//        fun action(title: String) {
//            val service = topActivity.getString(R.string.res_terms_of_service)
//            val privacy = topActivity.getString(R.string.res_privacy_policy)
//            if (title.contains(service)) {
//                action(title, ResConst.terms_of_service_url)
//            } else if (title.contains(privacy)) {
//                action(title, ResConst.privacy_policy_url)
//            }
//        }
    }
}