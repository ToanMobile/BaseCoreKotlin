/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

@file:Suppress("UseExpressionBody", "ClassName", "SpellCheckingInspection")

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension

internal val Project.androidBase: BaseExtension
    get() = (this as ExtensionAware).extensions.getByName("android") as BaseExtension

internal val Project.publishing: PublishingExtension
    get() =
        (this as ExtensionAware).extensions.getByName("publishing") as PublishingExtension

internal fun Project.publishing(configure: PublishingExtension.() -> Unit): Unit =
    (this as ExtensionAware).extensions.configure("publishing", configure)

internal fun Project.hasPublishing(): Boolean =
    (this as ExtensionAware).extensions.findByName("publishing") != null

fun Project.applyScript() {
//    apply(from = rootDir.path + "/script/ktlint_utils.gradle")
//    apply(from = rootDir.path + "/script/detekt.gradle.kts")
}

object GradlePluginId {
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val kotlinKapt = "kotlin-kapt"
}

fun RepositoryHandler.maven(url: String) {
    maven {
        setUrl(url)
    }
}

fun RepositoryHandler.applyDefault() {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://plugins.gradle.org/m2/")
}

private typealias DpdH = DependencyHandler

fun DpdH.classpathAll(list: List<String>) {
    list.forEach {
        add("classpath", it)
    }
}

fun DpdH.api(projectDependency: ProjectDependency) {
    add("api", projectDependency)
}

fun DpdH.implementation(projectDependency: ProjectDependency) {
    add("implementation", projectDependency)
}

fun DpdH.apiAll(list: List<String>) {
    list.forEach {
        add("api", it)
    }
}

fun DpdH.implementAll(list: List<String>) {
    list.forEach {
        add("implementation", it)
    }
}

fun DpdH.debugImplementAll(list: List<String>) {
    list.forEach {
        add("debugImplementation", it)
    }
}

fun DpdH.addPlugins(list: List<String>) {
    list.forEach {
        add(ScriptHandler.CLASSPATH_CONFIGURATION, it)
    }
}

fun DependencyHandler.kaptAll(list: List<String>) {
    list.forEach {
        add("kapt", it)
    }
}

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? = add("kapt", dependencyNotation)
