package tj.safarovsaburjon.sthelper.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseFragmentWithViewModel
import tj.safarovsaburjon.sthelper.vm.ProfileFragmentVM

class SettingsAdvancedFragment : BaseFragmentWithViewModel<ProfileFragmentVM>(
    R.layout.fragment_settings_advanced,
    ProfileFragmentVM::class.java
) {
    lateinit var textView:TextView
    var param = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.advancedTextView)
        textView.text = param
    }

    companion object {
        fun newInstance(param1: String) =
            SettingsAdvancedFragment().apply {
                param = param1
            }
    }
}