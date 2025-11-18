import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("therouter")
}
fun generateFileName(): String {
    val df = SimpleDateFormat("MM-dd HH-mm")
    val fileName = "${AppConfig.applicationName} ${df.format(Date())} ${AppConfig.versionCode} v${AppConfig.versionName}"
    return fileName
}
var fileName = ""

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true

        fileName = generateFileName()
        setProperty("archivesBaseName", fileName)
        proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        //设置支持的SO库架构
        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
//,'x86'//, 'armeabi-v7a', 'x86_64', 'arm64-v8a'//,'armeabi'
            // 添加更多的 abiFilters
        }
    }
    val properties = Properties()
    properties.load(project.rootProject.file(AppConfig.localProperties).inputStream())
    signingConfigs {
        create("release") {
            storeFile = file(properties.getProperty("signing.key"))
            keyAlias = properties.getProperty("RELEASE_KEY_ALIAS")
            keyPassword = properties.getProperty("RELEASE_KEY_PASSWORD")
            storePassword = properties.getProperty("RELEASE_STORE_PASSWORD")
            enableV2Signing = true
            enableV1Signing = true
        }
        getByName("debug") {
            storeFile = file(properties.getProperty("signing.key"))
            keyAlias = properties.getProperty("RELEASE_KEY_ALIAS")
            keyPassword = properties.getProperty("RELEASE_KEY_PASSWORD")
            storePassword = properties.getProperty("RELEASE_STORE_PASSWORD")
            enableV2Signing = true
            enableV1Signing = true
        }
    }
    buildTypes {
        val signConfig = signingConfigs.getByName("release")
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signConfig

        }
        getByName("debug") {
//            isMinifyEnabled = true
//            isShrinkResources = true
//            isZipAlignEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signConfig
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
        buildConfig = true
    }
}
dependencies {
    ksp(libs.therouter.apt)
    api(project(ModuleConfig.app_app))
}


val releaseDir = file("${rootProject.projectDir}/release/${AppConfig.versionName} ${AppConfig.versionCode}")

val copyReleaseFiles by tasks.registering {
    doLast {
        val versionDir = releaseDir
        val apkTargetDir = file("${versionDir}/apk/release")
        val mappingTargetDir = file("${versionDir}/apk/mapping/release")
        val apkDir = file("${buildDir}/outputs/apk/release")
        val mappingDir = file("${buildDir}/outputs/mapping/release")

        // 创建目标目录
        if (!versionDir.exists()) {
            versionDir.mkdirs()
        }
        if (!apkTargetDir.exists()) {
            apkTargetDir.mkdirs()
        }
        if (!mappingTargetDir.exists()) {
            mappingTargetDir.mkdirs()
        }

        // 复制 APK 文件到 release/apk/release 目录
        copy {
            from(apkDir)
            include("*.apk")
            into(apkTargetDir)
        }

        // 复制 mapping 文件到 release/mapping/release 目录
        copy {
            from(mappingDir)
            include("*")
            into(mappingTargetDir)
        }

        println("Release APK files copied to: $apkTargetDir")
        println("Release mapping files copied to: $mappingTargetDir")
    }
}
val copyBundleReleaseFiles by tasks.registering {
    doLast {
        val versionDir = releaseDir
        val bundleTargetDir = file("${versionDir}/bundle/release")
        val mappingTargetDir = file("${versionDir}/bundle/mapping/release")
        val bundleDir = file("${buildDir}/outputs/bundle/release")
        val mappingDir = file("${buildDir}/outputs/mapping/release")

        // 创建目标目录
        if (!versionDir.exists()) {
            versionDir.mkdirs()
        }
        if (!bundleTargetDir.exists()) {
            bundleTargetDir.mkdirs()
        }
        if (!mappingTargetDir.exists()) {
            mappingTargetDir.mkdirs()
        }

        // 复制 APK 文件到 release/apk/release 目录
        copy {
            from(bundleDir)
            include("$fileName-release.aab")
            into(bundleTargetDir)
        }

        // 复制 mapping 文件到 release/mapping/release 目录
        copy {
            from(mappingDir)
            include("*")
            into(mappingTargetDir)
        }

        println("Release bundle file name: $fileName-release.aab")
        println("Release bundle files copied to: $bundleTargetDir")
        println("Release mapping files copied to: $mappingTargetDir")
    }
}

afterEvaluate {
    tasks.named("assembleRelease").configure {
        finalizedBy(copyReleaseFiles)
    }
    tasks.named("bundleRelease").configure {
        finalizedBy(copyBundleReleaseFiles)
    }
}

