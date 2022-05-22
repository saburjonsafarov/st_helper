package tj.safarovsaburjon.sthelper.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.adapters.MainAdapter
import tj.safarovsaburjon.sthelper.core.base.BaseFragmentWithViewModel
import tj.safarovsaburjon.sthelper.repository.MainRepository
import tj.safarovsaburjon.sthelper.vm.AlarmFragmentVM

class AlarmFragment : BaseFragmentWithViewModel<AlarmFragmentVM>(
    R.layout.fragment_alarm,
    AlarmFragmentVM::class.java
), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.alarmRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MainAdapter(MainRepository().getList(), this)

    }

    override fun onClick(v: View?) {
        v?.let {

        }
    }
}