/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.netacom.base.chat.R
import com.netacom.base.chat.base.BaseViewModel
import com.netacom.base.chat.type.ScreenState
import com.toan_mobile.module.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

abstract class BaseDataFragmentNew<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layoutID: Int,
    private val viewModelClass: KClass<VM>
) : Fragment(), CoroutineScope {
    private var job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var viewModel: VM
    private var _binding: DB? = null
    val binding get() = _binding!!

    protected abstract fun initViews()
    protected abstract fun initData()
    protected abstract fun syncEvent()
    protected abstract fun setupTheme()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = getViewModel(clazz = viewModelClass)
        _binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        syncEvent()
        setupTheme()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    checkError()
                }
            }
        }
    }

    private suspend fun checkError() {
        (viewModel as? BaseViewModel)?.screenStateError?.collect {
            when (it.state) {
                ScreenState.NO_INTERNET -> showSnackBar(R.string.not_have_internet_please_check_your_connection)
                ScreenState.ERROR -> showSnackBar(it.message)
                ScreenState.EMPTY -> {}
                else -> showSnackBar(it.message)
            }
        }
    }

    fun <T> showSnackBar(text: T, textAction: Int? = null, action: View.OnClickListener? = null) {
//        if (ScreenUtil.againstALO3363(text.toString())) {
//            return
//        }
        activity?.findViewById<View>(android.R.id.content)?.apply {
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

    override fun onDestroyView() {
        job.cancel()
        _binding = null
        super.onDestroyView()
    }
}
