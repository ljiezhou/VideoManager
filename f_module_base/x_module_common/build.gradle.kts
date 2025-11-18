plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}
android {
    namespace = "com.ds.common"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            consumerProguardFiles += file("proguard-rules.pro")
        }
        debug {
            consumerProguardFiles += file("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = SDKConfig.COMPOSE_VERSION
    }
}

dependencies {
    api(libs.longan)
    api(libs.longan.design)
    api(libs.xpopup)
    api(libs.utilcodex)
//    api(libs.permixxionx)
    api(libs.xxpermixxion)
    api(libs.unpeek.livedata)
    api(libs.gson)

    api(libs.vasdolly.helper)
    api(libs.scwang90.refresh)
    api(libs.scwang90.refresh.header.classics)
    api(libs.scwang90.refresh.tooter.classics)
    api(libs.baseRecyclerViewAdapterHelper)

    api(libs.datastore.ktx)

    api(libs.multidex)
    api(libs.material)
    api(libs.kotlin.stdlib)

    api(libs.androidx.fragment)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.recyclerview)
    api(libs.androidx.constraintlayout)

    api(libs.navigation.ui.ktx)
    api(libs.navigation.fragment.ktx)

    api(libs.futures.ktx)
    api(libs.lifecycle.extensions)
    api(libs.lifecycle.viewmodel.ktx)
    api(libs.lifecycle.runtime)
    api(libs.lifecycle.runtime.ktx)
    api(libs.lifecycle.livedata.ktx)
    api(libs.lifecycle.common.java)

    ksp(libs.therouter.apt)
    api(libs.therouter)

//    debugImplementation(libs.debug.db)

    api(project(ModuleConfig.z_module_therouter))
    api(project(ModuleConfig.z_module_compose))
    api(project(ModuleConfig.z_module_image))
    api(project(ModuleConfig.z_module_res))
}