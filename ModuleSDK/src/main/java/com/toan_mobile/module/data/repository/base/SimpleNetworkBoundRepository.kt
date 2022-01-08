/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.repository.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Created by Tam Nguyen on 8/23/20.
 */
/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [REQUEST] represents the com.netacom.base.chat.type for com.netacom.base.chat.network.
 */

@ExperimentalCoroutinesApi
abstract class SimpleNetworkBoundRepository<REQUEST> {

    fun asFlow() = flow<State<REQUEST>> {

        // Emit Loading State
        emit(State.loading())

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remotePosts = apiResponse.body()

        // Check for response validation
        if (apiResponse.isSuccessful && remotePosts != null) {
            // Save posts into the persistence storage
            emit(State.successMerged(remotePosts))
            saveRemoteData(remotePosts)
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(apiResponse.message()))
        }
    }.catch { e ->
        // Exception occurred! Emit error
        emit(State.error("Network error! Can't get latest posts."))
        e.printStackTrace()
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}
