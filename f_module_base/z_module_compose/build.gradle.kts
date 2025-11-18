plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.ds.compose"

    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = SDKConfig.COMPOSE_VERSION
    }
}

dependencies {

    api(libs.compose.material.icons.core)
    api(libs.compose.material.icons.extended)
    api(libs.compose.material3.window.size)
    api(libs.compose.activity.compose)
    api(libs.compose.lifecycle.viewmodel.compose)
    api(libs.compose.runtime.livedata)
    api(libs.compose.runtime.rxjava2)

    api(platform(libs.compose.bom))
    api(libs.compose.material3)
    api(libs.compose.material)
    api(libs.compose.foundation)
    api(libs.compose.ui)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.ui.graphics)
    debugApi(libs.compose.ui.tooling)
    debugApi(libs.compose.ui.test.manifest)
    androidTestApi(libs.compose.ui.test.junit4)

    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
}