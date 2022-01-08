/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.gradle.kotlin.dsl.android
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType

plugins {
    androidLibrary
    baseGradlePlugin
    realmAndroid
    safeArgs
    protobuf
}
applyScript()

protobuf {
    protoc {
        artifact = Dependencies.protobuf
    }
    plugins {
        id("java") {
            artifact = Dependencies.protobufGenJava
        }
        id("grpc") {
            artifact = Dependencies.protobufGenJava
        }
        id("grpckt") {
            artifact = Dependencies.protobufGenKotlin
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("java") {
                    option("lite")
                }
                id("grpc") {
                    option("lite")
                }
                id("grpckt") {
                    option("lite")
                }
            }
            it.builtins {
                id("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

android {
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
    implementation(Dependencies.netAloBase)
    implementAll(AppDependencies.libraryAndroid)
    implementAll(AppDependencies.kotlin)
    implementAll(LifecyclerDependencies.viewModel)
    implementAll(CoreDependencies.libraryCore)
    implementAll(FireBaseDependencies.implementation)
    // Network
    implementAll(NetworkDependencies.network)
    implementAll(NetworkDependencies.grpc)
    implementAll(NetworkDependencies.jsonParse)
    // Library
    implementAll(ModuleDependencies.libraryOther)
    // Di
    implementAll(KoinDependencies.koin)
    kaptAll(AnnotationDependencies.kapt_core)
    apiAll(NavigationDependencies.implementation)
}
