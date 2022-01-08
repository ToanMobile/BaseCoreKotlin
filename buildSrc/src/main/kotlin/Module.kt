/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

private typealias PDsS = PluginDependenciesSpec
private typealias PDS = PluginDependencySpec

inline val PDsS.baseGradlePlugin: PDS get() = id("base-gradle-plugin")
inline val PDsS.androidApplication: PDS get() = id("com.android.application")
inline val PDsS.androidLibrary: PDS get() = id("com.android.library")
inline val PDsS.kotlin: PDS get() = id("kotlin")
inline val PDsS.kotlinAndroid: PDS get() = id("kotlin-android")
inline val PDsS.kotlinLibrary: PDS get() = id("kotlin-library")
inline val PDsS.kotlinKapt: PDS get() = id("kotlin-kapt")
inline val PDsS.kotlinParcelize: PDS get() = id("kotlin-parcelize")
inline val PDsS.daggerHilt: PDS get() = id("dagger.hilt.android.plugin")
inline val PDsS.safeArgs: PDS get() = id("androidx.navigation.safeargs.kotlin")
inline val PDsS.protobuf: PDS get() = id("com.google.protobuf")
inline val PDsS.realmAndroid: PDS get() = id("realm-android")
inline val PDsS.googleServices: PDS get() = id("com.google.gms.google-services")
inline val PDsS.firebasePerformance: PDS get() = id("com.google.firebase.firebase-perf")
inline val PDsS.crashlytics: PDS get() = id("com.google.firebase.crashlytics")
inline val PDsS.sentry: PDS get() = id("io.sentry.android.gradle")
inline val PDsS.kotlinxSerialization: PDS get() = id("kotlinx-serialization")

inline val DependencyHandler.core get() = project(":app")
inline val DependencyHandler.moduleSDK get() = project(":ModuleSDK")
inline val DependencyHandler.base get() = project(":SDKBase")
inline val DependencyHandler.testUtils get() = project(":test-utils")
