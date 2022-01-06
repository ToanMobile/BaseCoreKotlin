/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

object AnnotationDependencies {
    val kapt_core = listOf(
        Dependencies.lifeCompiler,
        Dependencies.realm_compiler,
    )

    val kapt_library = listOf(
        Dependencies.hilt_compiler,
        Dependencies.hilt_android_compiler
    )
}
