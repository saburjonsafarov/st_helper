package tj.safarovsaburjon.sthelper.vm

import android.icu.util.LocaleData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tj.safarovsaburjon.sthelper.core.base.BaseViewModel

/*
* tj.safarovsaburjon.sthelper.vm
* Created by Saburjon Safarov, on 21/05/2022
* email: safarovsaburjon2002@gmail.com
*/

class AlarmFragmentVM : BaseViewModel() {

    fun getNews() {
        return repository.getNews()
    }


    fun getList(): LiveData<String> {

        val list: MutableLiveData<String> = MutableLiveData()
        viewModelScope.launch {
            val data = repository.getList()
            list.postValue(data[0].name)
        }
        return list
    }
}