package tj.safarovsaburjon.sthelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.core.base.BaseFragmentWithViewModel
import tj.safarovsaburjon.sthelper.vm.AlarmFragmentVM

class ProfileFragment : BaseFragmentWithViewModel<AlarmFragmentVM>(
    R.layout.fragment_profile,
    AlarmFragmentVM::class.java
), View.OnClickListener {

    private lateinit var linearLayout: LinearLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayout = view.findViewById(R.id.profileLinearLayout)

        repeat(10) {
            val itemSeting = LayoutInflater.from(requireContext())
                .inflate(R.layout.setting_item, linearLayout, false)

            linearLayout.addView(itemSeting)
            itemSeting.tag = it
            itemSeting.setOnClickListener(this)

        }

    }

    override fun onClick(v: View?) {
        v?.let {
            Toast.makeText(context, (it.tag as Int).toString(), Toast.LENGTH_SHORT).show()
        }
    }

}