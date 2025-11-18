package com.ds.compress.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.compress.databinding.ImageHistoryFragmentBinding
import com.gyf.immersionbar.ktx.fitsTitleBar
import com.gyf.immersionbar.ktx.fitsTitleBarMarginTop

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class HistoryFragnebt : BaseVbFragment< ImageHistoryFragmentBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[HistoryViewModel::class.java] }
    override fun initView(savedInstanceState: Bundle?) {
        fitsTitleBarMarginTop(mViewBind.topLayout.container)

        mViewBind.topLayout.titleTv.text = "记录"

    }

    override fun createObserver() {
    }

    companion object {
        fun getInstance(): Fragment {
            val fragment = HistoryFragnebt()
            return fragment
        }
    }
}