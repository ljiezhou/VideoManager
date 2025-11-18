package com.ds.module.camerax

import android.util.Log
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig

/**
 * @Author ljiezhou
 * @date 2024/1/14
 * @Description
 */
val cameraHolder by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    CameraHolder()
}

class CameraHolder {
    fun cameraXConfig(): CameraXConfig {
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig())
            .setMinimumLoggingLevel(Log.ERROR).build()
    }
}