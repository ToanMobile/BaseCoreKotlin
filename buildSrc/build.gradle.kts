/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("base-gradle-plugin") {
            id = "base-gradle-plugin"
            implementationClass = "base_plugin.BaseGradlePlugin"
        }
    }
}

dependencies {
    implementation(dependencyNotation = "com.android.tools.build:gradle:7.0.4")
    implementation(dependencyNotation = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
}
