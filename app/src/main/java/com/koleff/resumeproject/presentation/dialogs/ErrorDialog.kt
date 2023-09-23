package com.koleff.resumeproject.presentation.dialogs

import com.koleff.resumeproject.common.utils.UIUtils
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import com.koleff.resumeproject.R
import com.koleff.resumeproject.databinding.DialogErrorBinding
import com.koleff.resumeproject.domain.wrappers.networkWrappers.KoleffError
import kotlinx.parcelize.Parcelize
import showUnique

typealias ErrorDialogListener = (ErrorDialog) -> Unit

class ErrorDialog: BaseDialog() {
    private var builder: ErrorDialog.Builder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.error_dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        builder = arguments?.getParcelable(Builder.BUILDER)
        builder?.run {
            if (positiveButton.active && negativeButton.active && neutralButton.active) {
                initButton(binding.buttonPositive, positiveButton)
                initButton(binding.buttonNegative, negativeButton)
                initButton(binding.buttonNeutral, neutralButton)

                binding.buttonPositive.layoutParams.width = UIUtils.DpToPx(100F)
                binding.buttonNegative.layoutParams.width = UIUtils.DpToPx(100F)
                binding.buttonNeutral.layoutParams.width = UIUtils.DpToPx(100F)

                binding.separator2.visibility = View.VISIBLE
                binding.separator1.visibility = View.VISIBLE
            } else if (positiveButton.active || negativeButton.active) {
                initButton(binding.buttonPositive, positiveButton)
                initButton(binding.buttonNegative, negativeButton)

                binding.separator2.visibility = View.VISIBLE
                binding.separator1.visibility = View.GONE
            } else if(neutralButton.active){
                initButton(binding.buttonNeutral, neutralButton)

                binding.separator1.visibility = View.GONE
                binding.separator2.visibility = View.GONE
            }else {
                setNeutralButton()
                initButton(binding.buttonNeutral, neutralButton)

                binding.separator1.visibility = View.GONE
                binding.separator2.visibility = View.GONE
            }

            initMessageBox(binding.tvMessage, messageBox)

            //When you touch outside of dialog bounds,
            //the dialog gets canceled and this method executes.
            dialog?.setOnCancelListener {
                dismissListener.invoke(this@ErrorDialog)
            }

            if (title != Builder.DEFAULT_TEXT)
                binding.tvTitle.text = title

            if (messageBox.text != Builder.DEFAULT_TEXT)
                binding.tvMessage.text = messageBox.text

            if (!titleVisible)
                binding.tvTitle.visibility = View.GONE
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        builder?.let {
                if (it.negativeButton.active) it.negativeButton.clickListener.invoke(this)
                else it.neutralButton.clickListener.invoke(this)
            }
        }

    private fun initMessageBox(view: TextView, messageBox: Builder.MessageBox){
        view.visibility = if(messageBox.active) View.VISIBLE else View.GONE

        if(!messageBox.hasTopMargin) {
            val lp = view.layoutParams as ConstraintLayout.LayoutParams
            lp.topMargin = UIUtils.DpToPx(2f)
            view.requestLayout()
        }

        view.text = messageBox.text
    }

    private fun initButton(view: TextView, button: Builder.Button) {
        view.visibility = View.VISIBLE
        if (button.text != Builder.DEFAULT_TEXT) {
            view.text = button.text
        }
        view.setTextColor(button.color)
        view.setOnClickListener { if(button.active)  button.clickListener.invoke(this)}
    }

    @Parcelize
    class Builder: Parcelable {
        companion object {
            const val TAG = "Error_dialog"
            const val BUILDER = "BUILDER"
            const val DEFAULT_TEXT = "DEFAULT_TEXT"
            var DEFAULT_COLOR = Color.parseColor("#b50000")
        }

        data class Button(
            var text: String = DEFAULT_TEXT, var active: Boolean = false,
            var clickListener: (ErrorDialog) -> Unit = defaultClickListener,
            var color: Int = DEFAULT_COLOR
        )

        data class MessageBox(
            var text: String = DEFAULT_TEXT,
            var active: Boolean = true,
            var hasTopMargin: Boolean = true
        )

        var title = DEFAULT_TEXT
            private set
        var messageBox = MessageBox(DEFAULT_TEXT)
            private set
        var neutralButton = Button()
            private set
        var positiveButton = Button()
            private set
        var negativeButton = Button()
            private set
        var error: KoleffError? = null
            private set
        var titleVisible: Boolean = true
            private set
        private var messageId: Int = -1

        private fun create(): ErrorDialog {
            val args = Bundle()
            args.putParcelable(BUILDER, this)

            val fragment = ErrorDialog()
            fragment.arguments = args
            return fragment
        }

        fun show(context: Context?, tag: String = TAG) {
            val currentContext = if (context is FragmentActivity) context else null

            currentContext?.apply {
                if (messageId != -1)
                    getMessageString(messageId, context!!)
                if (!isFinishing) create().showUnique(supportFragmentManager, tag)
            }
        }

        fun setTitle(title: String) = this.apply {
            this.title = title
        }

        fun setMessage(message: Int) = this.apply {
            this.messageBox.active = true
            messageId = message
        }

        fun setMessage(message: String, hasTopMargin: Boolean = true) = this.apply {
            this.messageBox.active = true
            this.messageBox.text = message
            this.messageBox.hasTopMargin = hasTopMargin
        }

        fun setNeutralButton(
            text: String = DEFAULT_TEXT, color: Int = DEFAULT_COLOR,
            clickListener: (error: ErrorDialog) -> Unit = defaultClickListener
        ) = this.apply {
            neutralButton.text = text
            neutralButton.clickListener = clickListener
            neutralButton.color = color
            neutralButton.active = true
        }


        fun setPositiveButton(
            text: String = DEFAULT_TEXT, color: Int = DEFAULT_COLOR,
            clickListener: (error: ErrorDialog) -> Unit = defaultClickListener
        ) = this.apply {
            positiveButton.text = text
            positiveButton.color = color
            positiveButton.clickListener = clickListener
            positiveButton.active = true
        }

        fun setNegativeButton(
            text: String = DEFAULT_TEXT, color: Int = DEFAULT_COLOR,
            clickListener: (error: ErrorDialog) -> Unit = defaultClickListener
        ) = this.apply {
            negativeButton.text = text
            negativeButton.color = color
            negativeButton.clickListener = clickListener
            negativeButton.active = true
        }

        fun setDismissListener(customDismissListener: ErrorDialogListener = dismissListener) =
            this.apply {
                dismissListener = customDismissListener
            }

        fun setError(error: KoleffError?) = this.apply {
            this.error = error
        }

        fun setTitleVisible(visibility: Boolean) = this.apply {
            titleVisible = visibility
        }

        private fun getMessageString(resId: Int, context: Context) {
            this.messageBox.text =
                if (resId != 0) context.getString(resId) else context.getString(R.string.text_oops_something_went_wrong)
        }
    }

    companion object {
        val defaultClickListener: ErrorDialogListener = { it.dismiss() }
        var dismissListener: ErrorDialogListener = { it.dismiss() }
    }
}