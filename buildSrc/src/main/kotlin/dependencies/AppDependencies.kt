/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

object AppDependencies {
    val libraryAndroid = listOf(
        Dependencies.appCompat,
        Dependencies.material,
        Dependencies.swiperefresh,
        Dependencies.constraintLayout,
        Dependencies.fragment,
        Dependencies.recyclerView,
        Dependencies.viewpager2,
        Dependencies.multidex,
        Dependencies.androidx_activity
    )

    val kotlin = listOf(
        Dependencies.kotlin,
        Dependencies.kotlinStdLib,
        Dependencies.kotlinReflect,
        Dependencies.kotlinExtensions
    )
}
