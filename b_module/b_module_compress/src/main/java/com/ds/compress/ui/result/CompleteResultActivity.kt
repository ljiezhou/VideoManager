package com.ds.compress.ui.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ds.common.base.activity.BaseVbActivity
import com.ds.compress.databinding.CompressResultActivityBinding

/**
 * @Author ljiezhou
 * @date 2024/10/29
 * @Description
 */
class CompleteResultActivity : BaseVbActivity<CompressResultActivityBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
    }

    override fun initListener() {
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CompleteResultActivity::class.java))
        }
    }
}