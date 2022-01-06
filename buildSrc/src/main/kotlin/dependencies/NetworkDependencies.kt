/*
 * *Created by ToanMobile
 * Email: hvtoan.dev@gmail.com.
 *  *
 */

object NetworkDependencies {
    val network = listOf(
        Dependencies.okHttpLoggingInterceptor,
        Dependencies.retrofit,
        Dependencies.okhttp,
    )

    val jsonParse = listOf(
        Dependencies.serialization,
        Dependencies.serializationConverter,
        Dependencies.serializationProtobuf,
    )

    val grpc = listOf(
        Dependencies.grpcAndroid,
        Dependencies.protobufOkhttp,
        Dependencies.protobufLite,
        Dependencies.protobufStub,
        Dependencies.protobufKotlinStub,
        Dependencies.protobufKotlinLite,
    )
}
