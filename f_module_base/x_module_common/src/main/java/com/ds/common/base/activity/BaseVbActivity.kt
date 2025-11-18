package com.ds.common.base.activity

import android.view.View
import androidx.viewbinding.ViewBinding
import com.ds.common.ext.inflateBindingWithGeneric2

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: 包含 ViewModel 和 ViewBinding ViewModelActivity基类，把ViewModel 和 ViewBinding 注入进来了
 * 需要使用 ViewBinding 的清继承它
 */
abstract class BaseVbActivity<VB : ViewBinding> : BaseActivity() {

    override fun layoutId(): Int = 0

    lateinit var mViewBind: VB

    /**
     * 创建DataBinding
     */
    override fun initDataBind(): View? {
        mViewBind = inflateBindingWithGeneric2(layoutInflater)
        return mViewBind.root

    }
}