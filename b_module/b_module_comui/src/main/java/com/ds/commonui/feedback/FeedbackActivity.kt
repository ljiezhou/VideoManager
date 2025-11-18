package com.ds.commonui.feedback

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ds.common.base.activity.BaseVbActivity
import com.ds.commonui.databinding.CommonUiFeedBackActivityBinding
import com.dylanc.longan.startActivity
import com.therouter.router.Route
import com.ds.common.therouter.Path
import com.ds.commonui.R
import com.ds.res.ext.setDarkToolbar
import com.dylanc.longan.toast

/**
 * @Author ljiezhou
 * @date 2023/8/15
 * @Description
 */
@Route(path = Path.App.FEEDBACKACTIVITY)
class FeedbackActivity : BaseVbActivity<CommonUiFeedBackActivityBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[FeedbackViewModel::class.java] }
    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.toolbar.setDarkToolbar(this, getString(com.module.res.R.string.feedback), true)
        mViewBind.feedBackBtn.setOnClickListener {
            mViewModel.feedback(mViewBind.feedBackContentEt.text.toString().trim())
        }
    }
//        finish()

    override fun createObserver() {
        mViewModel.updateUi.observe(this) {
            if (it.isSuccess) {
                toast(R.string.common_ui_feedback_success)
            } else {
                toast(R.string.common_ui_feedback_fail)
            }
            finish()
        }
    }

    override fun initListener() {
    }

    companion object {
        fun action() = startActivity<FeedbackActivity>()
    }
}
