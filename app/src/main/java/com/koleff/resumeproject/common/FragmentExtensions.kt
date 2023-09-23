import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.showUnique(manager: FragmentManager?, tag: String) {
    manager?.let {
        if (manager.findFragmentByTag(tag) == null && activity?.isFinishing != true) {
            manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
        }
    }
}