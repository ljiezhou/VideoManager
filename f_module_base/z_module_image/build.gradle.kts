plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
//    id("kotlin-kapt")
    id("com.google.devtools.ksp")
}
android {
    namespace = "com.ds.imageloader"
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
// app/build.gradle.kts 或 build.gradle
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/debug/kotlin") // for debug
        kotlin.srcDir("build/generated/ksp/release/kotlin") // for release
    }
}
dependencies {
    api(libs.kotlin.coroutines.core)

    api(libs.glide)
    ksp(libs.glide.ksp)

    api(libs.okhttp3.okhttp)
}