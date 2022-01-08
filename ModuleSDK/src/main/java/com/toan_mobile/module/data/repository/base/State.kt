/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.repository.base

/**
 * State Management for UI & Data.
 */
sealed class State<T> {
    class Loading<T> : State<T>()

    data class SuccessLocal<T>(val data: T) : State<T>()

    data class SuccessMerged<T>(val data: T) : State<T>()

    data class Error<T>(val message: String) : State<T>()

    companion object {

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> successLocal(data: T) = SuccessLocal(data)

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> successMerged(data: T) = SuccessMerged(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        fun <T> error(message: String) = Error<T>(message)
    }
}
