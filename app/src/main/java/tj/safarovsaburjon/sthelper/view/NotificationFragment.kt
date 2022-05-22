package tj.safarovsaburjon.sthelper.view

import android.os.Bundle
import android.view.View
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseFragment
import tj.safarovsaburjon.sthelper.core.base.BaseFragmentWithViewModel
import tj.safarovsaburjon.sthelper.vm.NotificationFragmentVM

/*
* tj.safarovsaburjon.sthelper.view
* Created by Saburjon Safarov, on 21/05/2022
* email: safarovsaburjon2002@gmail.com
*/

class NotificationFragment : BaseFragmentWithViewModel<NotificationFragmentVM>(
    R.layout.fragment_notification,
    NotificationFragmentVM::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}