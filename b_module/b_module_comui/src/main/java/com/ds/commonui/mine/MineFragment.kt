package com.ds.commonui.mine

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter4.QuickAdapterHelper
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.common.therouter.commonui.CommonUIManager
import com.ds.commonui.databinding.ScanMineFragmentBinding

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class MineFragment : BaseVbFragment<ScanMineFragmentBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[MineViewModel::class.java] }
    private val mineAdapter = MineAdapter()

    private val helper by lazy {
        QuickAdapterHelper.Builder(mineAdapter)
            .build()
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.mineRv.adapter = helper.adapter
        helper.addBeforeAdapter(0, MineHeadAdapter())
        helper.addAfterAdapter(MineAdAdapter())
    }

    override fun createObserver() {
        mViewModel.items.observe(this) {
            mineAdapter.submitList(it)
        }
    }

    override fun initListener() {
        mineAdapter.setOnItemClickListener { adapter, view, position ->
            mineAdapter.getItem(position)?.apply {
                when (titleId) {
                    com.module.res.R.string.res_terms_of_service, com.module.res.R.string.res_privacy_policy -> {
                        CommonUIManager.privacy(getString(titleId))
                    }

                    com.module.res.R.string.feedback -> CommonUIManager.feedback()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MineFragment"
        fun getInstance(): Fragment {
            val fragment = MineFragment()
            return fragment
        }
    }
}