package tj.safarovsaburjon.sthelper.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseFragmentWithViewModel
import tj.safarovsaburjon.sthelper.vm.ProfileFragmentVM

class AnalyticsFragment : BaseFragmentWithViewModel<ProfileFragmentVM>(
    R.layout.fragment_analytics,
    ProfileFragmentVM::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}