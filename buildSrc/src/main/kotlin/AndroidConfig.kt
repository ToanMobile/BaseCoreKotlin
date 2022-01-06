/**Created by vantoan on 06,Jan,2022
Email: hvtoan.dev@gmail.com.
Email: huynhvantoan.itc@gmail.com
 **/

object AndroidConfig {
    const val compileSdkVersion = 32
    const val targetSdkVersion = 32
    const val minSdkVersion = 23
    const val versionCode = 100
    const val versionName = "1.0.0"
    const val buildToolVersion = "32.0.0"
}

object GradleConfig {
    const val DEBUG: String = "debug"
    const val RELEASE: String = "release"
    const val buildBrand = Build.Brand.MyTel
}

object MyTelConfig {
    const val applicationName = "MyTel"
    const val applicationId = "com.mytel.android"
}

object SdkConfig {
    const val applicationName = "MyTelSdk"
    const val applicationId = "com.mytel.sdk"
}

interface BuildType {
    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = true
    override val isTestCoverageEnabled: Boolean = false

    const val pathProguard: String = "proguard"
    const val pathProguardLite: String = "lite-proguard"
    const val pathProguardFull: String = "full-proguard"
    const val pathKeyStore: String = "./keystore/keystore.properties"
}

interface EnvType {
    val environment: String
    val socketUrl: String
    val apiUrl: String
    val cdnUrl: String
    val profile: String
    val jitsiMeetUrl: String
    val stickerCdnUrl: String

    // WebRTC
    val iceTurnServer: String
    val iceStunServer: String
    val iceServerUsername: String
    val iceServerPassword: String

    // Config Sdk
    val joinGroup: String
    val urlCampaign: String
}

object EnvTypeDev : EnvType {
    override val socketUrl = ""
    override val apiUrl = ""
    override val cdnUrl = ""
    override val profile = ""
    override val jitsiMeetUrl = ""
    override val stickerCdnUrl = ""

    override val iceTurnServer = ""
    override val iceStunServer = ""
    override val iceServerUsername = "\"developer\""
    override val iceServerPassword = "\"password\""

    override val joinGroup = ""
    override val urlCampaign = ""

    override val environment: String = "Dev"
    const val applicationIdSuffix: String = ".dev"
    const val versionNameSuffix: String = "-dev"
}

object EnvTypeStaging : EnvType {
    override val socketUrl = ""
    override val apiUrl = ""
    override val cdnUrl = ""
    override val profile = ""
    override val jitsiMeetUrl = ""
    override val stickerCdnUrl = ""

    override val iceTurnServer = ""
    override val iceStunServer = ""
    override val iceServerUsername = "\"developer\""
    override val iceServerPassword = "\"password\""

    override val joinGroup = ""
    override val urlCampaign = ""

    override val environment: String = "Staging"
    const val applicationIdSuffix: String = ".staging"
    const val versionNameSuffix: String = "-staging"
}

object EnvTypeProductionDev {
    const val environment: String = "Prod"
    const val applicationIdSuffix: String = ".prod"
    const val versionNameSuffix: String = "-prod"
}

object EnvTypeProduction : EnvType {
    override val environment: String = ""
    override val socketUrl = ""
    override val apiUrl = ""
    override val cdnUrl = ""
    override val profile = ""
    override val jitsiMeetUrl = ""
    override val stickerCdnUrl = ""

    override val iceTurnServer = ""
    override val iceStunServer = ""
    override val iceServerUsername = ""
    override val iceServerPassword = ""

    override val joinGroup = ""
    override val urlCampaign = ""
}
