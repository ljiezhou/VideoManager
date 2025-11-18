package com.ds.common.base.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ds.common.netmanager.NetState

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelFragment基类，自动把ViewModel注入Fragment
 */

abstract class BaseFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())

    lateinit var mActivity: AppCompatActivity

    private var mIsInitData = false

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

    /**
     * 是否懒加载
     */
    open fun isLazyLoad(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initListener()
        createObserver()
        if (!isLazyLoad()) fetchData()
    }

    private fun fetchData() {
        if (mIsInitData) return
        initData()
        mIsInitData = true
    }

    open fun IsInitData(): Boolean {
        return mIsInitData
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    open fun initData() {}
    open fun initListener() {}
    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}