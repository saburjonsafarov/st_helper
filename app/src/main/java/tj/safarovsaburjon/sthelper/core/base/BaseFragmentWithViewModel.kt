package tj.safarovsaburjon.sthelper.core.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
* tj.safarovsaburjon.sthelper.core.base
* Created by Saburjon Safarov, on 18/05/2022
* email: safarovsaburjon2002@gmail.com
*/

abstract class BaseFragmentWithViewModel<VM : ViewModel>(layoutID: Int, vmClass: Class<VM>) :
    BaseFragment(layoutID) {
    protected val viewModel by lazy {
        ViewModelProvider(this)[vmClass]
    }
}