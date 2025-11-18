package com.ds.common.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ds.common.ext.inflateBindingWithGeneric2

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelFragment基类，自动把ViewModel注入Fragment和 ViewBinding 注入进来了
 * 需要使用 ViewBinding 的清继承它
 */
abstract class BaseVbFragment< VB : ViewBinding> : BaseFragment() {

    override fun layoutId() = 0

    //该类绑定的 ViewBinding
    private var _binding: VB? = null
    val mViewBind: VB get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = inflateBindingWithGeneric2(inflater,container,false)
        return mViewBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}