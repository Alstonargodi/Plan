package com.example.wetterbericht.presentation.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.databinding.ItemcvTodoSubtaskBinding
import com.example.wetterbericht.presentation.componen.TaskTitleView

class SubDetailRecyclerViewAdapter(
    private val subTask : MutableList<TodoSubTask>,
    val onChecked: (TodoSubTask, Boolean) -> Unit
) : RecyclerView.Adapter<SubDetailRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemcvTodoSubtaskBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemcvTodoSubtaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = subTask[position]
        holder.binding.tvTitleSubtask.text = data.title
        holder.binding.btnCheckSubtodo.setOnClickListener {
            onChecked(data, !data.isComplete)
        }
        holder.binding.btnCheckSubtodo.visibility = View.VISIBLE
        holder.binding.btnCheckSubtodo.isChecked = data.isComplete

        when{
            data.isComplete ->{
                holder.binding.tvTitleSubtask.state = TaskTitleView.DONE
            }
        }
    }

    fun removeItem(position: Int) {
        subTask.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = subTask.size
}