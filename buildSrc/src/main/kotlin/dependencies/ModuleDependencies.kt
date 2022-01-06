/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

object ModuleDependencies {
    val implementationUI = listOf(
        Dependencies.view_photo,
        Dependencies.exoPlayer,
        Dependencies.touch_imageView,
        // Dependencies.Zxing,
        Dependencies.kpermissions,
        // Dependencies.jitsiMeet,
        Dependencies.facebook_share,
        Dependencies.auth,
        Dependencies.authApi,
        Dependencies.countryCodePicker,
        Dependencies.netAloBase
    )

    val implementationData = listOf(
        Dependencies.libphonenumber,
        // Dependencies.lightcompressor,
        // Dependencies.socketIO,
    )

    val libraryOther = listOf(
        Dependencies.kpermissions,
        Dependencies.libphonenumber,
        Dependencies.jsoup,
        Dependencies.materialChip
        // Dependencies.google_ads,
        // Dependencies.gson,
        // Dependencies.converter_gson
    )
}
