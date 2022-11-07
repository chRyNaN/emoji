import com.chrynan.emoji.buildSrc.LibraryConstants
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("org.jetbrains.compose")
}

group = LibraryConstants.group
version = LibraryConstants.versionName

kotlin {
    android {
        publishAllLibraryVariants()
    }
    targets {
        android()
        jvm()
        ios()
        iosSimulatorArm64()
        js(IR) {
            browser()
        }
    }
    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
        val commonMain by getting {
            dependencies {
                api(project(":emoji-core"))

                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.material)

                implementation("com.chrynan.ui-components:ui-components-image:0.1.13")
            }
        }
        val iosMain by sourceSets.getting
        val iosSimulatorArm64Main by sourceSets.getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

dependencies {
    // Android Emoji Support Libraries
    api("androidx.emoji:emoji:1.1.0")
    api("androidx.emoji:emoji-appcompat:1.1.0")
    api("androidx.emoji:emoji-bundled:1.1.0")
    api("de.c1710:filemojicompat:3.2.6")
}

android {
    compileSdk = LibraryConstants.Android.compileSdkVersion

    defaultConfig {
        minSdk = LibraryConstants.Android.minSdkVersion
        targetSdk = LibraryConstants.Android.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            // Opt-in to experimental compose APIs
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].java.srcDirs("src/androidMain/kotlin")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    sourceSets["test"].java.srcDirs("src/androidTest/kotlin")
    sourceSets["test"].res.srcDirs("src/androidTest/res")
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INHERIT }

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
