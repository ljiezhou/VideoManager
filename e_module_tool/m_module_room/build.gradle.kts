plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ds.room"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "DATA_BASE_NAME", "\"${AppConfig.DATA_BASE_NAME}\"")
        buildConfigField("int", "DATA_BASE_VERSION", "${AppConfig.DATA_BASE_VERSION}")
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
}

dependencies {

//    api RoomLibs.INSTANCE.values
//    kapt RoomLibs.INSTANCE.roomcompiler

    api(libs.room.ktx)
    api(libs.room.runtime)
    ksp(libs.room.compiler)
//    ksp(libs.room.compiler)
    api(libs.paging.paging)
    api(libs.paging.runtime)
    api(libs.paging.common)
    ksp(libs.therouter.apt)

    api(project(":f_module_base:x_module_common"))
//    api project(':f_module:x_module_common')

    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
}
