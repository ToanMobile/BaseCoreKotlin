/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 10:16 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 10:16 PM
 *
 */

package com.toan_mobile.module.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.netacom.base.chat.R
import com.netacom.base.chat.android_utils.ScreenUtils
import com.netacom.base.chat.base.BaseViewModel
import com.netacom.base.chat.define.DialogDef
import com.toan_mobile.module.BR
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseDialogBottomFragmentNew<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layoutID: Int,
    private val viewModelClass: KClass<VM>
) : BottomSheetDialogFragment() {
    protected abstract fun setLayoutHeight(): Int
    private var _binding: DB? = null
    val binding get() = _binding!!
    lateinit var viewModel: VM
    var isLayout: Int = DialogDef.LAYOUT_NORMAL
    var allowDraggable: Boolean = true
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setDecorFitsSystemWindows(false)
                ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
                    val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                    binding.root.setPadding(0, 0, 0, imeHeight)
                    insets
                }
            } else {
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            }
            // setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED)
        }
        (view?.parent as? View)?.apply {
            BottomSheetBehavior.from(this).apply {
                if (!allowDraggable) {
                    isDraggable = false
                    dialog?.setCanceledOnTouchOutside(false)
                }
                when (isLayout) {
                    DialogDef.LAYOUT_SMALL -> setPeekHeight(ScreenUtils.getScreenHeight() / 2, true)
                    DialogDef.LAYOUT_NORMAL -> setPeekHeight(
                        (ScreenUtils.getScreenHeight() / 1.3).toInt(),
                        true
                    )
                    DialogDef.LAYOUT_FULL -> {
                        val sheetContainer = requireView().parent as? ViewGroup ?: return
                        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                        setPeekHeight(ScreenUtils.getScreenHeight(), true)
                        state = BottomSheetBehavior.STATE_EXPANDED
                    }
                    DialogDef.LAYOUT_WRAP_CONTENT -> {
                        val sheetContainer = requireView().parent as? ViewGroup ?: return
                        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        setPeekHeight(ScreenUtils.getScreenHeight(), true)
                        state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
                isDraggable = allowDraggable
            }
        } ?: run {
            FirebaseCrashlytics.getInstance().recordException(NullPointerException("BaseDialogBottomFragment:BottomSheetBehavior:Null"))
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)

        return dialog
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = getViewModel(clazz = viewModelClass)
        _binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        isLayout = setLayoutHeight()
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
        dialog?.setOnShowListener { _dialogInterface ->
            val bottomSheetDialog = _dialogInterface as BottomSheetDialog
            setColorNavigationBar(bottomSheetDialog)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        setupTheme()
    }

    protected abstract fun initViews()

    protected abstract fun initData()

    protected abstract fun setupTheme()

    open fun setColorNavigationBar(dialog: Dialog) {
    }

    fun <T> showSnackBar(
        text: T,
        textAction: Int? = null,
        action: View.OnClickListener? = null,
        currentView: View? = null
    ) {
        val view = currentView ?: this.dialog?.window?.findViewById<View>(android.R.id.content)
        view?.let {
            val snackBar = when (text) {
                is Int -> Snackbar.make(it, getString(text), Snackbar.LENGTH_LONG)
                else -> Snackbar.make(it, text.toString(), Snackbar.LENGTH_LONG)
            }
            if (textAction != null && action != null) {
                snackBar.setAction(getString(textAction), action)
            }
            val snackBarView = snackBar.view
            snackBarView.translationY = (-getNavigationBarSize(requireContext())).toFloat()
            snackBar.show()
        }
    }

    private fun getNavigationBarSize(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else context.resources.displayMetrics.density.toInt() * 56
    }

    override fun onDestroyView() {
        dialog?.dismiss()
        _binding = null
        super.onDestroyView()
    }
}
