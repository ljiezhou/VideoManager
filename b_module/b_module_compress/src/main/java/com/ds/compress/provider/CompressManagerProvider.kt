package com.ds.compress.provider

import android.app.Application
import com.ds.compress.R
import com.ds.compress.ui.home.HomeFragment
import com.ds.res.NavItem
import com.ds.therouter.service.compress.CompressManagerService
import com.therouter.inject.ServiceProvider


@ServiceProvider
fun getCompressManagerProvider(): CompressManagerService {
    return object : CompressManagerService {
        override fun init(application: Application, channel: String) {

        }

        override fun getNavItems(): List<NavItem> {
            val navItems = mutableListOf<NavItem>()
            navItems.add(NavItem(R.id.compress_home_id, HomeFragment.getInstance(), R.string.compress_home_tab_title, R.drawable.app_main_nav_icon_home, true))
            return navItems
        }

    }
}