import com.chrynan.emoji.buildSrc.LibraryConstants
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.dokka")
}

group = LibraryConstants.group
version = LibraryConstants.versionName

android {
    compileSdk = LibraryConstants.Android.compileSdkVersion

    defaultConfig {
        minSdk = LibraryConstants.Android.minSdkVersion
        targetSdk = LibraryConstants.Android.targetSdkVersion
        versionCode = LibraryConstants.versionCode
        versionName = LibraryConstants.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            minifyEnabled(false)
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.android.support:support-annotations:28.0.0")

    implementation("androidx.appcompat:appcompat:1.2.0")

    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    // Kotlin Coroutine Dispatchers
    implementation("com.chrynan.dispatchers:dispatchers:0.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    // Adapter - aaaah
    implementation("com.chrynan.aaaah:aaaah-libraryx:0.9.0")
    implementation("com.chrynan.aaaah:aaaah-annotation:0.9.0")
    kapt("com.chrynan.aaaah:aaaah-compiler:0.9.0")

    // Android Emoji Support Libraries
    api("androidx.emoji:emoji:1.1.0")
    api("androidx.emoji:emoji-appcompat:1.1.0")
    api("androidx.emoji:emoji-bundled:1.1.0")
    api("de.c1710:filemojicompat:1.0.18")

    // Image Loading - Coil
    api("io.coil-kt:coil:1.1.0")

    // Android Pre-computed Text Utils
    api("com.chrynan.precomputedtext:precomputedtext-android:0.1.0-release")

    api(project(":emoji-presentation-core"))
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                url = uri("https://repo.repsy.io/mvn/chrynan/public")

                credentials {
                    username = (project.findProperty("repsyUsername") ?: System.getenv("repsyUsername")) as? String
                    password = (project.findProperty("repsyToken") ?: System.getenv("repsyToken")) as? String
                }
            }
        }
    }
}
