package tj.safarovsaburjon.sthelper.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import tj.safarovsaburjon.sthelper.R
import tj.safarovsaburjon.sthelper.model.StudentModel
import tj.safarovsaburjon.sthelper.repository.MainRepository

/*
* tj.safarovsaburjon.sthelper.core.adapters
* Created by Saburjon Safarov, on 21/05/2022
* email: safarovsaburjon2002@gmail.com
*/

class MainAdapter(val list: List<StudentModel>, private val onClickListener: View.OnClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item, parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position == list.lastIndex)
            holder.itemView.setPadding(0, 0, 0, 100)

        val state = list[position]

        holder.apply {
            tView.text = state.liked.toString()
            itemView.tag = itemView
            itemView.setOnClickListener(onClickListener)
            likeBox.isChecked = state.liked
            likeBox.setOnClickListener {
                MainRepository.items[position].liked = likeBox.isChecked
            }
        }


    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tView: TextView = view.findViewById(R.id.itemTextView)
        val imageView: ImageView = view.findViewById(R.id.itemImageView)
        val likeBox: CheckBox = view.findViewById(R.id.itemLikeBox)
    }
}