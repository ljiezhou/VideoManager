package com.ds.common.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.common.ext.getVmClazz

/**
 * @Author ljiezhou
 * @date 2023/12/24
 * @Description
 */
abstract class BaseComposeFragment<VM : BaseViewModel> : Fragment() {

    lateinit var mViewModel: VM
    lateinit var mActivity: AppCompatActivity

    private var mIsInitData = false

    protected abstract fun initData()
    protected abstract fun initObserver()
    protected abstract fun initListener()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    /**
     * 是否懒加载
     */
    protected open fun isLazyLoad(): Boolean {
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = createViewModel()

        initObserver()
        initListener()
        if (!isLazyLoad()) fetchData()
    }


    open fun fetchData() {
        if (mIsInitData) return
        initData()
        mIsInitData = true
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    abstract fun ComposeView(): ComposeView
    protected lateinit var mView: ComposeView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = ComposeView()
        return mView
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVmClazz(this)]
    }
}