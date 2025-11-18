package com.ds.app.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.FragmentUtils
import com.ds.app.databinding.AppMainActivityBinding
import com.ds.common.base.activity.BaseVbActivity
import com.ds.app.ui.main.adapter.MainAdapter
import com.ds.app.ui.main.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.view.get

class MainActivity : BaseVbActivity<AppMainActivityBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private var mainAdapter: MainAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
//        BarUtils.setStatusBarLightMode(this, true)

        mainAdapter = MainAdapter(supportFragmentManager)
        mViewBind.viewPager.adapter = this.mainAdapter
        mViewBind.viewPager.offscreenPageLimit = 3

        mViewBind.navView.itemIconTintList = null
        mViewBind.navView.labelVisibilityMode = BottomNavigationView.LABEL_VISIBILITY_LABELED
    }

    override fun createObserver() {
        mViewModel.navs.observe(this) {
            mViewBind.navView.apply {
                if (it.size < 2) {
                    visibility = View.GONE
//                    setVisible(false)
                } else {
//                    visibility = View.VISIBLE
                    setVisible(true)
                }
                mainAdapter?.clear()
                it.forEachIndexed { index, navItem ->
                    menu.add(0, navItem.id, index, navItem.title).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                    menu.getItem(index).setIcon(navItem.icon)
                    mainAdapter?.addFragment(navItem.fragment)
                }
                mainAdapter?.notifyDataSetChanged()

                val bottomNavigationView = getChildAt(0) as ViewGroup
                for (position in it.indices) {
                    bottomNavigationView.getChildAt(position)?.findViewById<BottomNavigationItemView>(it[position].id)?.setOnLongClickListener { true }
                }
            }
        }
    }

    private var currentPosition = 0
    override fun initListener() {
        mViewBind.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentPosition = position
                mViewBind.navView.menu[position].isChecked = true
                if (mViewModel.navs.value == null) {
                    false
                }

                mViewBind.viewPager.post {
                    BarUtils.setStatusBarLightMode(this@MainActivity, mViewModel.navs.value!![position].isLight)
                }
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        mViewBind.navView.setOnItemSelectedListener {
            if (mViewModel.navs.value == null) {
                false
            }
            for (position in mViewModel.navs.value!!.indices) {
                val bottomNavigationItem = mViewModel.navs.value!![position]
                if (bottomNavigationItem.id == it.itemId) {
                    mViewBind.viewPager.setCurrentItem(position, false)
                    true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        val topFragment = FragmentUtils.getTopShowInStack(supportFragmentManager)
        if (topFragment != null) {
            FragmentUtils.remove(topFragment)
            return
        }
        super.onBackPressed()
    }

    companion object {
        fun action(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity).finish()
        }
    }
}

