plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.ds.roundcorners"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
//        targetSdk = AppConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            consumerProguardFiles += file("proguard-rules.pro")
            resValue("string", "common_applicationId", AppConfig.applicationId)
        }
        debug {
            consumerProguardFiles += file("proguard-rules.pro")
            resValue("string", "common_applicationId", AppConfig.applicationId)
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
    api(libs.kotlin.stdlib)
    api(libs.utilcodex)

    api(libs.material)

    api(libs.androidx.activity)
    api(libs.androidx.activity.ktx)
    api(project(ModuleConfig.z_module_app_name))
    api(project(ModuleConfig.z_module_screenmatch))

    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
}
configurations.all {
    resolutionStrategy.force("androidx.fragment:fragment:1.3.6")
}

