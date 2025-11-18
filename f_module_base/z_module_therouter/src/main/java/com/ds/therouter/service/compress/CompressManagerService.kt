package com.ds.therouter.service.compress

import android.app.Application
import com.ds.res.NavItem

/**
 * @Author ljiezhou
 * @date 2024/6/17
 * @Description
 */
interface CompressManagerService {
    fun init(application: Application, channel: String)

    fun getNavItems(): List<NavItem>
}
