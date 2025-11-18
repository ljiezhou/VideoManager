package com.ds.common.base.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import com.ds.common.ext.util.notNull
import com.ds.common.netmanager.NetState
import com.ds.common.netmanager.NetworkStateManager
import com.therouter.TheRouter

//import com.therouter.TheRouter

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseActivity : AppCompatActivity() {
    protected val mContext: Context = this

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    open fun onGlobalLayoutListener() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TheRouter.inject(this)

        initDataBind().notNull({
            setContentView(it)
        }, {
            setContentView(layoutId())
        })
        val rootView = window.decorView
        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                onGlobalLayoutListener()
            }
        })
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        createObserver()
        initListener()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this) {
            onNetworkStateChanged(it)
        }
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()
    abstract fun initListener()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
//            onBackPressedDispatcher.onBackPressed()
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind(): View? {
        return null
    }
}