package com.ds.therouter.service

import android.app.Application

/**
 * @Author ljiezhou
 * @date 2024/6/17
 * @Description
 */
interface BuglyService {
    fun init(application: Application, channel: String)
}
