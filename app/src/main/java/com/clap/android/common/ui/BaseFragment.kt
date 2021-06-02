package com.clap.android.common.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.clap.android.common.utils.LocaleUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    lateinit var mNavController: NavController

    private var progressDialog: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            mNavController = findNavController()
        } catch (e: IllegalStateException) {

        }

        observeUI()
        setOnClickListeners()

        // by default no bottom or top margins
        addFragmentMargins()
    }

    abstract fun observeUI()

    abstract fun setOnClickListeners()

    protected fun showMessage(title: String? = null,
                              message: String? = null,
                              positiveActionText: String = "Ok",
                              negativeActionText: String = "",
                              positiveAction: () -> Unit = {},
                              negativeAction: () -> Unit = {}) {

    }

    protected fun showSnackBar(message: String) {
        view?.let { v -> // make sure that view is not null to prevent crashes.
            val snackBar = Snackbar.make(v, message, Snackbar.LENGTH_SHORT)
            snackBar.anchorView = requireActivity().navViewBottom
            snackBar.show()
        }
    }

    protected fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    protected fun addFragmentMargins(
        addTopMargin: Boolean = false,
        addBottomMargin: Boolean = false
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(requireActivity().findViewById(android.R.id.content)) { view, insets ->
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            if (addBottomMargin) params.bottomMargin =
                insets.systemWindowInsetBottom else params.bottomMargin = 0
            if (addTopMargin) params.topMargin = insets.systemWindowInsetTop else params.topMargin =
                0
            insets //insets.consumeSystemWindowInsets()
        }
    }

    fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressBar(requireContext())
        }
        progressDialog?.let {
            if (!it.isVisible) {
                it.isVisible = true
            }
        }
    }

    fun hideProgressDialog() {
        progressDialog?.isVisible = false
    }
}