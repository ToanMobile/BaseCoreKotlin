/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.repository.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
Created by vantoan on 05/Aug/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/
/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the com.netacom.base.chat.type for database.
 * [REQUEST] represents the com.netacom.base.chat.type for com.netacom.base.chat.network.
 */
@ExperimentalCoroutinesApi
abstract class SocketBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow {

        // Emit Loading State
        emit(State.loading())

        try {
            // Emit Database content first
            emit(State.successLocal(fetchFromLocal().first()))

            // Fetch latest posts from remote
            val data = fetchFromRemote()
            // Check for response validation
            if (data != null) {
                // Save posts into the persistence storage
                saveRemoteData(data)
            } else {
                // Something went wrong! Emit Error state.
                // emit(State.error(data.toString()))
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(State.error(e.message ?: "error"))
            e.printStackTrace()
        }

        // Retrieve posts from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                State.successLocal(it)
            }
        )
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): REQUEST
}
