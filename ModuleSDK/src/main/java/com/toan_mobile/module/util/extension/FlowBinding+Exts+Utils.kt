/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.util.extension

import android.content.Context
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CheckResult
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.ViewDataBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
fun EditText.firstChange(): Flow<Unit> {
    return callbackFlow<Unit> {
        checkMainThread()

        val listener = doOnTextChanged { _, _, _, _ -> trySend(Unit) }
        awaitClose {
            Dispatchers.Main.dispatch(EmptyCoroutineContext) {
                removeTextChangedListener(listener)
                // Timber.d("removeTextChangedListener $listener $this")
            }
        }
    }.take(1)
}

@ExperimentalCoroutinesApi
@CheckResult
fun SwipeRefreshLayout.refreshes(): Flow<Unit> {
    return callbackFlow {
        checkMainThread()
        setOnRefreshListener { this.trySend(Unit).isSuccess }
        awaitClose { setOnRefreshListener(null) }
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun View.clicks(): Flow<View> {
    return callbackFlow {
        checkMainThread()
        setOnClickListener { this.trySend(it).isSuccess }
        awaitClose { setOnClickListener(null) }
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        checkMainThread()

        val listener = doOnTextChanged { text, _, _, _ -> trySend(text).isSuccess }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun <T, R> Flow<T>.flatMapFirst(transform: suspend (value: T) -> Flow<R>): Flow<R> =
    map(transform).flattenFirst()

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun <T> Flow<Flow<T>>.flattenFirst(): Flow<T> = channelFlow {
    val outerScope = this
    val busy = AtomicBoolean(false)
    collect { inner ->
        if (busy.compareAndSet(false, true)) {
            launch {
                try {
                    inner.collect { outerScope.send(it) }
                    busy.set(false)
                } catch (e: CancellationException) {
                    // cancel outer scope on cancellation exception, too
                    outerScope.cancel(e)
                }
            }
        }
    }
}

private object UNINITIALIZED

@InternalCoroutinesApi
fun <A, B, R> Flow<A>.withLatestFrom(other: Flow<B>, transform: suspend (A, B) -> R): Flow<R> {
    return flow {
        coroutineScope {
            val latestB = AtomicReference<Any>(UNINITIALIZED)
            val outerScope = this

            launch {
                try {
                    other.collect { latestB.set(it) }
                } catch (e: CancellationException) {
                    outerScope.cancel(e) // cancel outer scope on cancellation exception, too
                }
            }

            collect { a ->
                val b = latestB.get()
                if (b != UNINITIALIZED) {
                    @Suppress("UNCHECKED_CAST")
                    emit(transform(a, b as B))
                }
            }
        }
    }
}

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
suspend fun main() {
    (1..2000).asFlow()
        .onEach { delay(50) }
        .flatMapFirst { v ->
            flow {
                delay(500)
                emit(v)
            }
        }
        .onEach { println("[*] $it") }
        .catch { println("Error $it") }
        .collect()
}

inline fun <T> avoidException(
    allowPrint: Boolean = false,
    exceptionBlock: (e: Exception) -> T? = { null },
    finallyBlock: () -> T? = { null },
    tryBlock: () -> T? = { null }
) =
    try {
        tryBlock()
    } catch (e: Exception) {
        if (allowPrint)
            e.printStackTrace()
        exceptionBlock(e)
        FirebaseCrashlytics.getInstance().recordException(e)
    } finally {
        finallyBlock()
    }

inline fun <T : View?> T.afterMeasured(
    observeForEver: Boolean = false,
    crossinline block: T.() -> Unit
) =
    this?.viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                avoidException {
                    block()
                    if (!observeForEver)
                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        })

inline fun <T : ViewDataBinding?> T.afterMeasured(
    crossinline block: View?.() -> Unit
) = this?.root.afterMeasured(block = block)

internal fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "Expected to be called on the main thread but was " + Thread.currentThread().name
    }
}

data class SearchViewQueryTextEvent(
    val view: SearchView,
    val query: CharSequence,
    val isSubmitted: Boolean,
)

@ExperimentalCoroutinesApi
@CheckResult
fun SearchView.queryTextEvents(): Flow<SearchViewQueryTextEvent> {
    return callbackFlow {
        checkMainThread()

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                trySend(
                    SearchViewQueryTextEvent(
                        view = this@queryTextEvents,
                        query = query,
                        isSubmitted = true,
                    )
                )
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                trySend(
                    SearchViewQueryTextEvent(
                        view = this@queryTextEvents,
                        query = newText,
                        isSubmitted = false,
                    )
                )
                return true
            }
        })

        awaitClose { setOnQueryTextListener(null) }
    }.onStart {
        emit(
            SearchViewQueryTextEvent(
                view = this@queryTextEvents,
                query = query,
                isSubmitted = false,
            )
        )
    }
}

fun Context.toast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
