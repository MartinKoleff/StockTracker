package com.koleff.resumeproject.common

import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError

object ErrorHandler {

    private val activeActivity = KoleffApp.getActiveActivity()

    fun showError(error: KoleffError? = null, errorMessage: String? = null) {
        val neutralButtonText = activeActivity.getString(R.string.text_ok)
        val positiveButtonText = activeActivity.getString(R.string.text_yes)
        val negativeButtonText = activeActivity.getString(R.string.text_cancel)

        val dialogError: KoleffError = error ?: KoleffError.GENERIC
        val dialogMessage: String = errorMessage ?: activeActivity.getString(dialogError.errorMessage)

//        val dialogMessage = errorMessage
//            ?: activeActivity.getString(error?.errorMessage ?: dialogError.errorMessage)

        ErrorDialog.Builder()
            .setMessage(dialogMessage)
            .setError(error)
            .setNeutralButton(neutralButtonText)
            .setPositiveButton(positiveButtonText)
            .setNegativeButton(negativeButtonText)
            .show(activeActivity)
    }
}