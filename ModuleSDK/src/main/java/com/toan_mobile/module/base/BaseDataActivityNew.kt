/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.base

import android.os.Bundle
import android.view.View
import androidx.annotation.Keep
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.netacom.base.chat.R
import com.netacom.base.chat.base.BaseViewModel
import com.netacom.base.chat.type.ScreenState
import com.toan_mobile.module.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

@Keep
abstract class BaseDataActivityNew<DB : ViewDataBinding?, VM : ViewModel>(
    @LayoutRes val layoutID: Int?,
    private val viewModelClass: KClass<VM>
) :
    AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private var _binding: DB? = null
    val binding get() = _binding!!
    lateinit var viewModel: VM
    protected abstract fun initViews()
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutID?.let { layout ->
            viewModel = getViewModel(clazz = viewModelClass)
            _binding = DataBindingUtil.setContentView(this, layout) as? DB
            _binding?.apply {
                lifecycleOwner = this@BaseDataActivityNew
                setVariable(BR.viewModel, viewModel)
            }
        }
        initViews()
        initData()
        // checkError()
    }

    private suspend fun checkError() {
        (viewModel as? BaseViewModel)?.screenStateError?.collect {
            when (it.state) {
                ScreenState.NO_INTERNET -> showSnackBar(R.string.not_have_internet_please_check_your_connection)
                ScreenState.ERROR -> showSnackBar(it.message)
                else -> showSnackBar(it.message)
            }
        }
    }

    fun <T> showSnackBar(text: T, textAction: Int? = null, action: View.OnClickListener? = null) {
//        if (ScreenUtil.againstALO3363(text.toString())) {
//            return
//        }
        findViewById<View>(android.R.id.content)?.apply {
            val snackBar = when (text) {
                is Int -> Snackbar.make(this, getString(text), Snackbar.LENGTH_LONG)
                else -> Snackbar.make(this, text.toString(), Snackbar.LENGTH_LONG)
            }
            if (textAction != null && action != null) {
                snackBar.setAction(getString(textAction), action)
            }
            snackBar.show()
        }
    }

    override fun onStop() {
        super.onStop()
        job.cancelChildren()
    }

    override fun onDestroy() {
        // Logger.e("onDestroy::" + this.javaClass.simpleName)
        _binding = null
        super.onDestroy()
    }
}
