package com.ds.commonsdk.umeng

import android.app.Application
import android.content.Context
import android.util.Log
import com.ds.therouter.service.UmengService
import com.therouter.inject.ServiceProvider


/**
 * @Author ljiezhou
 * @date 2024/6/17
 * @Description
 */
//@ServiceProvider(returnType = UmengService::class, params = [Context::class])
//fun create(context: Context, str: String): UmengService = object : UmengService {
//    override fun init() {
//        uMHolder.init(context as Application, str)
//    }
//}

@ServiceProvider
fun test(): UmengService {
    return object : UmengService {
        override fun init(application: Application, channel: String) {
            uMHolder.init(application, channel)
        }
    }

}

private const val TAG = "UmengServiceProvider"