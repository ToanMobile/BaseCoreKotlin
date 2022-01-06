/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

include(":module")


val app = "app"
val module = "module"

settingsDir
    .listFiles()
    .orEmpty()
    .forEach { dir ->
        if (dir.name == module) {
            include(":${dir.name}")
        }
        if (dir.name == app) {
            include(":${dir.name}")
        }
    }
