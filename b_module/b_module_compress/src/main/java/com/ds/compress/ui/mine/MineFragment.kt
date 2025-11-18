package com.ds.compress.ui.mine

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.compress.databinding.ImageMineFragmentBinding

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class MineFragment : BaseVbFragment<ImageMineFragmentBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[MineViewModel::class.java] }
    val mineAdapter = MineAdapter()

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.mineRv.adapter = mineAdapter
    }

    override fun createObserver() {
        mViewModel.items.observe(this) {
            mineAdapter.submitList(it)
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