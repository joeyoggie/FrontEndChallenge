package com.clap.android.common.ui

import android.content.Context
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.clap.android.common.utils.LocaleUtils

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: ProgressBar? = null

    override fun attachBaseContext(newBase: Context) {
        LocaleUtils.updateConfig(this)
        super.attachBaseContext(newBase)
    }

    protected fun addActivityMargins(
        addTopMargin: Boolean = false,
        addBottomMargin: Boolean = true
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            if (addBottomMargin) params.bottomMargin = insets.systemWindowInsetBottom
            if (addTopMargin) params.topMargin = insets.systemWindowInsetTop
            insets //insets.consumeSystemWindowInsets()
        }
    }

    fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressBar(this)
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