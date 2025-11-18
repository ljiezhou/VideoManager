plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.ds.net"
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
        viewBinding = true
    }
}

dependencies {
//    api(libs.retrofit.url.manager)
    api("me.jessyan:retrofit-url-manager:1.4.0")
    api(libs.gson)
    api(libs.okhttp3.okhttp)
    api(libs.okhttp3.logging.interceptor)
    api(libs.squareup.retrofit2.retrofit)
    api(libs.squareup.retrofit2.converter.gson)
    api(libs.squareup.retrofit2.adapter.rxjava3)

    api(project(ModuleConfig.x_module_common))
}