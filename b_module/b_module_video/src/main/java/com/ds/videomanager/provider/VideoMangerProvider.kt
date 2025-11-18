package com.ds.videomanager.provider

import android.app.Application
import com.ds.res.NavItem
import com.therouter.inject.ServiceProvider
import com.ds.therouter.service.videomanager.VideoManagerService
import com.ds.videomanager.R
import com.ds.videomanager.ui.VideoManagerHomeFragment

@ServiceProvider
fun getVideoManagerProvider(): VideoManagerService {
    return object : VideoManagerService {
        override fun init(application: Application, channel: String) {

        }

        override fun getNavItems(): List<NavItem> {
            val navItems = mutableListOf<NavItem>()
//            navItems.add(VideoManagerHomeFragment.getInstance())
            navItems.add(NavItem(R.id.home_tab_video_manager_id, VideoManagerHomeFragment.getInstance(), R.string.video_manager_tab_title, R.drawable.baseline_video_camera_front_24, true))
            return navItems
        }

    }
}