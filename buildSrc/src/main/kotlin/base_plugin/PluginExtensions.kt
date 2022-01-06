/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

@file:Suppress("UnstableApiUsage")

package base_plugin

import AndroidConfig
import Build
import BuildTypeRelease
import GradleConfig
import GradlePluginId
import com.android.build.api.dsl.ApplicationBuildFeatures
import com.android.build.api.dsl.DynamicFeatureBuildFeatures
import com.android.build.api.dsl.LibraryBuildFeatures
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

internal fun Project.configureDefaultPlugins() {
    plugins.apply(GradlePluginId.kotlinAndroid)
    plugins.apply(GradlePluginId.kotlinParcelize)
    plugins.apply(GradlePluginId.kotlinKapt)
}

internal fun Project.configureAndroidApp() = this.extensions.getByType<BaseExtension>().run {
    when (buildFeatures) {
        is ApplicationBuildFeatures -> {
            (buildFeatures as ApplicationBuildFeatures).dataBinding = true
        }
        is LibraryBuildFeatures -> {
            (buildFeatures as LibraryBuildFeatures).dataBinding = true
        }
        is DynamicFeatureBuildFeatures -> {
            (buildFeatures as DynamicFeatureBuildFeatures).dataBinding = true
        }
    }
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    buildToolsVersion(AndroidConfig.buildToolVersion)
    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        multiDexEnabled = true
        ndk.abiFilters("armeabi", "armeabi-v7a", "x86_64", "x86", "arm64-v8a")
        vectorDrawables.useSupportLibrary = true
        renderscriptTargetApi = 19
        renderscriptSupportModeEnabled = true
    }

    val keystoreProperties = Properties()
    val keystorePropertiesFile = rootProject.file(BuildTypeRelease.pathKeyStore)
    if (keystorePropertiesFile.exists()) {
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    }
    signingConfigs {
        if (keystorePropertiesFile.exists()) {
            getByName(GradleConfig.DEBUG) {
                storeFile = file(keystoreProperties.getProperty("storeFileDebug"))
                storePassword = keystoreProperties.getProperty("keyStorePasswordDebug")
                keyAlias = keystoreProperties.getProperty("keyAliasDebug")
                keyPassword = keystoreProperties.getProperty("keyStorePasswordDebug")
            }
            create(GradleConfig.RELEASE) {
                if (GradleConfig.buildBrand == Build.Brand.Sdk) {
                    storeFile = file(keystoreProperties.getProperty("storeFileReleaseSDK"))
                    storePassword = keystoreProperties.getProperty("keyStorePasswordReleaseSDK")
                    keyAlias = keystoreProperties.getProperty("keyAliasReleaseSDK")
                    keyPassword = keystoreProperties.getProperty("keyStorePasswordReleaseSDK")
                } else {
                    storeFile = file(keystoreProperties.getProperty("storeFileReleaseMyTel"))
                    storePassword = keystoreProperties.getProperty("keyStorePasswordReleaseMyTel")
                    keyAlias = keystoreProperties.getProperty("keyAliasReleaseMyTel")
                    keyPassword = keystoreProperties.getProperty("keyStorePasswordReleaseMyTel")
                }
                enableV3Signing = true
                enableV4Signing = true
            }
        }
    }
    flavorDimensions(Build.Brand.value, Build.Env.value)
    productFlavors {
        create(Build.Env.dev) {
            dimension = Build.Env.value
        }
        create(Build.Env.production) {
            dimension = Build.Env.value
        }
        if (GradleConfig.buildBrand == Build.Brand.MyTel) {
            create(Build.Env.stg) {
                dimension = Build.Env.value
            }
        }
        if (GradleConfig.buildBrand == Build.Brand.MyTel) {
            create(Build.Env.production_dev) {
                dimension = Build.Env.value
            }
        }
        if (GradleConfig.buildBrand == Build.Brand.MyTel) {
            create(Build.Brand.MyTel) {
                dimension = Build.Brand.value
                resValue(type = "string", name = "maps_api_key", value = "AIzaSyD2gVRUBJFwENILQaP9Vx_3LAsE7aaTeOQ")
                resValue(type = "string", name = "provider_facebook", value = "com.facebook.app.FacebookContentProvider335442037583549")
            }
        }
        if (GradleConfig.buildBrand == Build.Brand.Sdk) {
            create(Build.Brand.Sdk) {
                dimension = Build.Brand.value
                resValue(type = "string", name = "maps_api_key", value = "AIzaSyBDZnXunX8pVoURf0HNbr9YNcZ9dZVWf_E")
                resValue(type = "string", name = "provider_facebook", value = "com.facebook.app.FacebookContentProvider472102537368428")
            }
        }
    }

    lintOptions {
        isCheckReleaseBuilds = true
        isCheckAllWarnings = true
        isAbortOnError = false
        lintConfig = project.rootProject.file(".lint/config.xml")
        isWarningsAsErrors = false
    }
    tasks.withType<KotlinCompile>() {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packagingOptions {
        resources {
            excludes.addAll(
                listOf(
                    "DebugProbesKt.bin", "META-INF/notice.txt", "META-INF/DEPENDENCIES", "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md", "META-INF/kotlinx-io.kotlin_module", "META-INF/atomicfu.kotlin_module", "META-INF/kotlinx-coroutines-io.kotlin_module"
                )
            )
            pickFirsts.add("**/lib/**")
        }
    }
}

fun Project.configureSubProject() = subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
            // allWarningsAsErrors = true
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.OptIn",
                "-Xopt-in=kotlin.Experimental",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }
    afterEvaluate {
        configurations.all {
            resolutionStrategy {
                // force("net.sf.proguard:proguard-gradle:6.2.2")
                eachDependency {
                    if (requested.group == "androidx.activity" && requested.name == "activity-ktx") {
                        useVersion("1.4.0")
                    }
                    if (requested.group == "androidx.appcompat" && requested.name == "appcompat") {
                        useVersion("1.4.0")
                    }
                    if (requested.group == "androidx.recyclerview" && requested.name == "recyclerview") {
                        useVersion("1.2.1")
                    }
                    if (requested.group == "androidx.constrainlayout" && requested.name == "constrainlayout") {
                        useVersion("2.1.2")
                    }
                    if (requested.group == "org.jetbrains.kotlin" && !requested.name.startsWith("kotlin-gradle")) {
                        useVersion("1.6.10")
                    }
                    if (requested.group == "org.jetbrains.kotlin" && requested.name == "kotlin-reflect") {
                        useVersion("1.6.10")
                    }
                    if (requested.group == "androidx.core" && requested.name == "core-ktx") {
                        useVersion("1.7.0")
                    }
                    if (requested.group == "androidx.navigation" && requested.name == "navigation-ui-core-ktx") {
                        useVersion("2.4.0-rc01")
                    }
                    if (requested.group == "androidx.lifecycler" && requested.name == "lifecycler-livedata-core-ktx") {
                        useVersion("2.4.0")
                    }
                    if (requested.group == "androidx.lifecycler" && requested.name == "lifecycler-viewmodel-core-ktx") {
                        useVersion("2.4.0")
                    }
                }
            }
        }
    }
}
