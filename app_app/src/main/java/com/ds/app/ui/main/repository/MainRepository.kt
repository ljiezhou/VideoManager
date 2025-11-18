package com.ds.app.ui.main.repository

import com.ds.res.NavItem
import com.ds.therouter.service.compress.CompressManagerService
import com.ds.therouter.service.videomanager.VideoManagerService
import com.therouter.TheRouter

class MainRepository {

    companion object {
        fun getItems(): ArrayList<NavItem> {
            val items = arrayListOf<NavItem>()
            TheRouter.get(CompressManagerService::class.java)?.let {
                items.addAll(it.getNavItems())
            }
            TheRouter.get(VideoManagerService::class.java)?.let {
                items.addAll(it.getNavItems())
            }
            return items
        }
    }
}