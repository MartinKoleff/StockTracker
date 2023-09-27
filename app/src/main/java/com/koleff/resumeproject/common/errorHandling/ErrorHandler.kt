package com.koleff.resumeproject.common.errorHandling

import android.app.Activity
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError
import com.koleff.resumeproject.presentation.dialogs.ErrorDialog
import java.lang.ref.WeakReference

object ErrorHandler {

    private val activeActivity: WeakReference<Activity> by lazy {WeakReference(KoleffApp.getActiveActivity())}

    fun showError(error: KoleffError? = null, errorMessage: String? = null) {
        activeActivity.get() != null || return

        val neutralButtonText = activeActivity.get()!!.getString(R.string.text_ok)
        val positiveButtonText = activeActivity.get()!!.getString(R.string.text_yes)
        val negativeButtonText = activeActivity.get()!!.getString(R.string.text_cancel)

        val dialogError: KoleffError = error ?: KoleffError.GENERIC
        val dialogMessage: String =
            errorMessage ?: activeActivity.get()!!.getString(dialogError.errorMessage)

        val errorDialog = ErrorDialog.Builder()
            .setMessage(dialogMessage)
            .setError(error)

        when (dialogError) {
            KoleffError.GENERIC -> {
                errorDialog.setNeutralButton(neutralButtonText)
                    .setPositiveButton(positiveButtonText)
                    .setNegativeButton(negativeButtonText)
            }

            else -> {
                errorDialog
                    .setPositiveButton(positiveButtonText)
                    .setNegativeButton(negativeButtonText)
            }
        }

        errorDialog.show(activeActivity.get()!!)
    }
}