/*
 * *Created by MyTelTeamAndroid on 2020
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

plugins {
    androidApplication
    baseGradlePlugin
    safeArgs
    googleServices
    crashlytics
}

android {
    buildTypes {
        getByName(GradleConfig.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.isTestCoverageEnabled
            isCrunchPngs = BuildTypeDebug.isTestCoverageEnabled
            signingConfig = signingConfigs.getByName(GradleConfig.DEBUG)
            extra["alwaysUpdateBuildId"] = false
            extra["enableCrashlytics"] = false
        }
        getByName(GradleConfig.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isDebuggable = BuildTypeRelease.isTestCoverageEnabled
            isCrunchPngs = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isMinifyEnabled
            val files = rootProject.file(BuildTypeRelease.pathProguard)
                .listFiles()
                ?.filter { it.name.startsWith(BuildTypeRelease.pathProguard) }
                ?.toTypedArray()
            proguardFiles(*files ?: arrayOf())
            signingConfig = signingConfigs.getByName(GradleConfig.RELEASE)
        }
    }
    flavorDimensions.clear()
    flavorDimensions.addAll(listOf(Build.Brand.value, Build.Env.value))
    productFlavors {
        if (GradleConfig.buildBrand == Build.Brand.MyApp) {
            getByName(Build.Brand.MyApp) {
                dimension = Build.Brand.value
                applicationId = MyTelConfig.applicationId
            }
        }
        if (GradleConfig.buildBrand == Build.Brand.Sdk) {
            getByName(Build.Brand.Sdk) {
                dimension = Build.Brand.value
                applicationId = SdkConfig.applicationId
            }
        }
        getByName(Build.Env.dev) {
            dimension = Build.Env.value
            applicationIdSuffix = EnvTypeDev.applicationIdSuffix
            versionNameSuffix = EnvTypeDev.versionNameSuffix
            val appName = if (GradleConfig.buildBrand == Build.Brand.Sdk) SdkConfig.applicationName else MyTelConfig.applicationName
            resValue("string", "app_name", "$appName ${EnvTypeDev.environment}")
        }
        getByName(Build.Env.production) {
            dimension = Build.Env.value
            val appName = if (GradleConfig.buildBrand == Build.Brand.Sdk) SdkConfig.applicationName else MyTelConfig.applicationName
            resValue("string", "app_name", appName)
        }
        if (GradleConfig.buildBrand == Build.Brand.MyApp) {
            getByName(Build.Env.stg) {
                dimension = Build.Env.value
                applicationIdSuffix = EnvTypeStaging.applicationIdSuffix
                versionNameSuffix = EnvTypeStaging.versionNameSuffix
                resValue("string", "app_name", "${MyTelConfig.applicationName} ${EnvTypeStaging.environment}")
            }
        }
        if (GradleConfig.buildBrand == Build.Brand.MyApp) {
            getByName(Build.Env.production_dev) {
                dimension = Build.Env.value
                applicationIdSuffix = EnvTypeProductionDev.applicationIdSuffix
                versionNameSuffix = EnvTypeProductionDev.versionNameSuffix
                resValue("string", "app_name", "${MyTelConfig.applicationName} ${EnvTypeProductionDev.environment}")
            }
        }
    }

    defaultConfig {
        setProperty("archivesBaseName", "v$versionName($versionCode)")
    }

    bundle {
        abi.enableSplit = true
        density.enableSplit = false
        language.enableSplit = false
    }

    sourceSets {
        getByName("main").apply {
            file("src/main/res-screen").listFiles()?.forEach {
                res.srcDirs(it.path)
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(moduleSDK)
    implementation(Dependencies.netAloBase)
    implementAll(AppDependencies.kotlin)
    kaptAll(AnnotationDependencies.kapt_library)
}
