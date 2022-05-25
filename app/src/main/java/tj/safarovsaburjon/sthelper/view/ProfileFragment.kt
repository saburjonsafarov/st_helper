package tj.safarovsaburjon.sthelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
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

        repeat(5) { itm ->
            val groupItem = LayoutInflater.from(requireContext())
                .inflate(R.layout.settings_group_item, linearLayout, false)

            repeat(4 + itm) {
                val item = LayoutInflater.from(requireContext())
                    .inflate(R.layout.setting_item, linearLayout, false)
                item.tag = it + (itm + 1) * 10
                item.setOnClickListener(this)
                groupItem.findViewById<LinearLayout>(R.id.settingsGroupLinearLayout).addView(item)
            }
            groupItem
                .findViewById<TextView>(R.id.settingsGroupTitle)
                .text = "group: ${(itm + 1) * 10} settings property "
            linearLayout.addView(groupItem)

        }
    }

    override fun onClick(v: View?) {
        v?.let {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.containerDetail,
                    SettingsAdvancedFragment.newInstance((it.tag as Int).toString())
                )
                .addToBackStack("add")
                .commit()
        }
    }

}