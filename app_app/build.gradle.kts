plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ds.app"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        manifestPlaceholders += mapOf("UMENG_APPKEY" to SDKConfig.UM_APP_KEY)
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
        viewBinding = true
    }
}
dependencies {
    ksp(libs.therouter.apt)
    api(project(ModuleConfig.b_module_compress))
    api(project(ModuleConfig.b_module_video_manager))
    api(project(ModuleConfig.b_module_comui))
    api(project(ModuleConfig.m_module_ad))

    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
}

