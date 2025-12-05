// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.therouter) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
//    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
//    alias(libs.plugins.ksp) apply false
//    id("com.google.devtools.ksp") version "2.2.0-1.0.20"
//    id("com.google.devtools.ksp") version "1.9.10-1.0.13" // 根据 Kotlin 版本选择合适的 ksp 版本
}