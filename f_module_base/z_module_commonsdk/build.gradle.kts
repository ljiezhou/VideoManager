plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ds.commonsdk"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")


        buildConfigField("String", "BUGLY_APPID", "\"${SDKConfig.BUGLY_APPID}\"")
        buildConfigField("String", "BUGLY_APPKEY", "\"${SDKConfig.BUGLY_APPKEY}\"")

        buildConfigField("String", "UM_APP_KEY", "\"${SDKConfig.UM_APP_KEY}\"")
        buildConfigField("String", "UM_PUSHSE", "\"${SDKConfig.UM_PUSHSE}\"")

        buildConfigField("String", "APPLICATION_ID", "\"${AppConfig.applicationId}\"")
        buildConfigField("String", "APPLICATION_VERSION_NAME", "\"${AppConfig.versionName}\"")
    }

    buildTypes {
        release {
            consumerProguardFiles += file("consumer-rules.pro")
        }
        debug {
            consumerProguardFiles += file("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(libs.bugly)
    ksp(libs.therouter.apt)

    api(libs.google.gms)
//    api(libs.umeng.common)
//    api(libs.umeng.asms)
//    api(libs.umeng.uyumao)

    api(project(ModuleConfig.z_module_therouter))

    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
}
