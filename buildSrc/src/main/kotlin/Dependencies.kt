/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

@file:Suppress("SpellCheckingInspection")

object Dependencies {
    const val gradle = "com.android.tools.build:gradle:${Versions.com_android_tools_build_gradle}"

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_stdlib}"
    const val kotlinCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin_stdlib}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_stdlib}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_stdlib}"

    // Plugin
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_stdlib}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin_stdlib}"
    const val kotlinExtensions = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val playServices = "com.google.gms:google-services:${Versions.google_service}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics_plugin}"
    const val realmPlugin = "io.realm:realm-gradle-plugin:${Versions.realm}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val sentryPlugin = "io.sentry:sentry-android-gradle-plugin:${Versions.sentryPlugin}"

    // Format Code
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"

    // Test
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val blockcanary = "com.github.bzcoder:blockcanarycompat-android:${Versions.blockcanary}"
    const val firebase_per = "com.google.firebase:firebase-perf-ktx:${Versions.firebase_per}"

    // AppCompat
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val androidx_activity = "androidx.activity:activity-ktx:${Versions.androidx_activity}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hilt_android_compiler = "androidx.hilt:hilt-compiler:${Versions.view_model_hilt}"
    const val hilt_work = "androidx.hilt:hilt-work:${Versions.view_model_hilt}"

    // Koin
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinViewModel = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinappCombat = "io.insert-koin:koin-android-compat:${Versions.koin}"
    const val koin_worker = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val koin_navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"

    // Lifecycer
    const val lifeCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.androidx_lifecycle}"
    const val lifecyclerViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle}"
    const val lifecyclerRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle}"
    const val lifecycerProcess = "androidx.lifecycle:lifecycle-process:${Versions.androidx_lifecycle}"
    const val lifecyclerSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidx_lifecycle}"

    // Image
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilVideo = "io.coil-kt:coil-video:${Versions.coil}"
    const val lightcompressor = "com.github.AbedElazizShe:LightCompressor:${Versions.lightcompressor}"

    // Realm
    const val realm_compiler = "io.realm:realm-annotations-processor:${Versions.realm}"

    // Work
    const val work_manager = "androidx.work:work-runtime-ktx:${Versions.work}"

    // Navigation
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_ui_ktx}"
    const val navigationDynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation_ui_ktx}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_ui_ktx}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_ui_ktx}"

    // Network
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    const val serializationProtobuf = "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:${Versions.serialization}"
    const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serializationConvert}"

    // FireBase
    const val firebase_analytic = "com.google.firebase:firebase-analytics-ktx:${Versions.analytic}"
    const val firebase_messaging = "com.google.firebase:firebase-messaging-ktx:${Versions.messaging}"
    const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics-ktx:${Versions.crashlytics}"
    const val google_base = "com.google.android.gms:play-services-base:${Versions.google_base}"
    const val google_basement = "com.google.android.gms:play-services-basement:${Versions.google_base}"

    // Security
    const val biometric = "androidx.biometric:biometric-ktx:${Versions.biometric}"
    const val security = "androidx.security:security-crypto-ktx:${Versions.security}"
    const val tink = "com.google.crypto.tink:tink-android:${Versions.tink}"

    // Authencation
    const val auth = "com.google.android.gms:play-services-auth:${Versions.google_auth}"
    const val authApi = "com.google.android.gms:play-services-auth-api-phone:${Versions.google_auth_phone}"

    // Core
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val utilcode = "com.blankj:utilcodex:${Versions.utilCode}"
    const val dataStore = "androidx.datastore:datastore:${Versions.data_store_version}"
    const val dataStorePreference = "androidx.datastore:datastore-preferences:${Versions.data_store_version}"

    // WebRTC
    const val webrtc = "org.webrtc:google-webrtc:${Versions.webRtc}"

    // Library
    // const val shimmer = "com.facebook.shimmer:shimmer:${Versions.simmerVersion}"
    const val netAloBase = "vn.netacom:NetAloBaseChat-Dev:${Versions.netAloBaseVersion}"
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val kpermissions = "com.github.fondesa:kpermissions:${Versions.kpermission}"

    // Facebook share
    const val facebook_share = "com.facebook.android:facebook-share:[5,6)"

    // View Photo
    const val view_photo = "com.github.chrisbanes:PhotoView:${Versions.photo_view}"

    // Touch Image View
    const val touch_imageView = "com.github.MikeOrtiz:TouchImageView:${Versions.image_touch}"

    // Exo Player
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoPlayer}"

    // Secret
    const val curve25519Android = "org.whispersystems:curve25519-android:${Versions.curve25519}"
    const val signalProtocol = "org.whispersystems:signal-protocol-android:${Versions.signalProtocol}"
    const val shotwatch = "com.abangfadli.shotwatch:shotwatch:${Versions.shotwatch}"

    // Jitsi Meet
    const val jitsiMeet = "org.jitsi.react:jitsi-meet-sdk:${Versions.jitsiMeet}"
    const val countryCodePicker = "com.hbb20:ccp:${Versions.countryPicker}"

    // Phone
    const val libphonenumber = "io.michaelrocks:libphonenumber-android:${Versions.phoneNumberVersion}"

    // GRPC
    const val googleProtobuf = "com.google.protobuf:protobuf-gradle-plugin:${Versions.grpcPlugin}"

    const val protobuf = "com.google.protobuf:protoc:${Versions.protobufVersion}"

    const val protobufGenJava = "io.grpc:protoc-gen-grpc-java:${Versions.protobufLiteVersion}"

    const val protobufGenKotlin = "io.grpc:protoc-gen-grpc-kotlin:${Versions.protobufKotlinVersion}:jdk7@jar"

    const val protobufKotlinStub = "io.grpc:grpc-kotlin-stub:${Versions.protobufKotlinVersion}"

    const val protobufKotlinLite = "com.google.protobuf:protobuf-kotlin-lite:${Versions.protobufVersion}"

    const val grpcAndroid = "io.grpc:grpc-android:${Versions.protobufLiteVersion}"

    const val protobufOkhttp = "io.grpc:grpc-okhttp:${Versions.protobufLiteVersion}"

    const val protobufLite = "io.grpc:grpc-protobuf-lite:${Versions.protobufLiteVersion}"

    const val protobufStub = "io.grpc:grpc-stub:${Versions.protobufLiteVersion}"

    const val googleGrpc = "com.google.api.grpc:googleapis-common-protos:${Versions.googleGrpcVersion}"

    const val materialChip = "com.github.pchmn:MaterialChipsInput:${Versions.materialChipVersion}"
}
