package com.ds.commonsdk.bugly

import android.app.Application
import com.ds.therouter.service.BuglyService
import com.therouter.inject.ServiceProvider


/**
 * @Author ljiezhou
 * @date 2024/6/17
 * @Description
 */

@ServiceProvider
fun test(): BuglyService {
    return object : BuglyService {
        override fun init(application: Application, channel: String) {
            buglyHolder.init(application, channel)
        }
    }
}