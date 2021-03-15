package tj.mediasavod.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tj.mediasavod.BR
import tj.mediasavod.R
import tj.mediasavod.ItemClickListener
import tj.mediasavod.databinding.QuizItemBinding
import tj.mediasavod.models.Data
import java.util.ArrayList

class QuizAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<QuizAdapter.Holder>() {
    var list: MutableList<Data> = ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: QuizItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.quiz_item,
                parent,
                false
            )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(val item: QuizItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(obj: Any, clickListener: ItemClickListener) {
            item.itemClick=clickListener
            item.setVariable(BR.item, obj)
            item.executePendingBindings()
        }
    }

}