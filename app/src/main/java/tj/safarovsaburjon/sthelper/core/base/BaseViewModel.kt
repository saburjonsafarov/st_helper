package tj.safarovsaburjon.sthelper.core.base

import androidx.lifecycle.ViewModel
import tj.safarovsaburjon.sthelper.repository.MainRepository

/*
* tj.safarovsaburjon.sthelper.core
* Created by Saburjon Safarov, on 18/05/2022
* email: safarovsaburjon2002@gmail.com
*/

abstract class BaseViewModel():ViewModel() {
    protected val repository: MainRepository = MainRepository()

    suspend fun getList(q: String) {

    }

}