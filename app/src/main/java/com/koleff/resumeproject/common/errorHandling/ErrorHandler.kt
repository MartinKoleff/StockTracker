package com.koleff.resumeproject.common.errorHandling

import android.app.Activity
import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError
import com.koleff.resumeproject.presentation.dialogs.ErrorDialog

object ErrorHandler {
    fun showError(activity: Activity, error: KoleffError? = null, errorMessage: String? = null) {
        val neutralButtonText = activity.getString(R.string.text_ok)
        val positiveButtonText = activity.getString(R.string.text_yes)
        val negativeButtonText = activity.getString(R.string.text_cancel)

        val dialogError: KoleffError = error ?: KoleffError.GENERIC
        val dialogMessage: String =
            errorMessage ?: activity.getString(dialogError.errorMessage)

        val errorDialog = ErrorDialog.Builder()
            .setMessage(dialogMessage)
            .setError(error)

        when (dialogError) {
            KoleffError.GENERIC -> {
                errorDialog.setNeutralButton(neutralButtonText)
                    .setPositiveButton(positiveButtonText)
                    .setNegativeButton(negativeButtonText)
            }
            //TODO: add other errors...
            else -> {
                errorDialog
                    .setPositiveButton(positiveButtonText)
                    .setNegativeButton(negativeButtonText)
            }
        }

        errorDialog.show(activity)
    }
}