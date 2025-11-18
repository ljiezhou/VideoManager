package com.ds.app

//import androidx.camera.core.CameraXConfig
import com.ds.common.base.BaseApp
//import com.ds.module.camerax.cameraHolder

/**
 * @Author ljiezhou
 * @date 2023/6/26
 * @Description
 */
class FoodApplication : BaseApp(){//}, CameraXConfig.Provider {

    override fun _onCreate() {
    }

    companion object {
        private const val TAG = "FoodApplication"
    }

//    override fun getCameraXConfig(): CameraXConfig {
//        return cameraHolder.cameraXConfig()
//    }
}