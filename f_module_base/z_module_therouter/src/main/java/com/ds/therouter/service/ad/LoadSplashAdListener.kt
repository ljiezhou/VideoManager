package com.ds.therouter.service.ad

interface LoadSplashAdListener {
    fun onADExposure()
    fun onADTick(millisUntilFinished: Long)
    fun onADClicked()
    fun close()
}