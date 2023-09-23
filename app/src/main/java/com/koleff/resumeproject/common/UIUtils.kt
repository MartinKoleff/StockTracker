import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import com.koleff.resumeproject.KoleffApp

object UIUtils {
    fun DpToPx(dp: Float): Int {
        val metrics: DisplayMetrics = KoleffApp.getAppContext().resources.displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.toInt()
    }

    fun PxToDp(px: Float): Int {
        val metrics: DisplayMetrics = KoleffApp.getAppContext().resources.displayMetrics
        val dp = px / (metrics.densityDpi / 160f)
        return dp.toInt()
    }

    @Deprecated("to update")
    fun showKeyboard(context: Context) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        view?.let {
            view.clearFocus()
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
