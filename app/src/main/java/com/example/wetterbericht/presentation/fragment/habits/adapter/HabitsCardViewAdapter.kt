package com.example.wetterbericht.presentation.fragment.habits.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.data.local.entity.habits.HabitsLocal
import com.example.wetterbericht.databinding.ItemcvHabitsBinding
import com.example.wetterbericht.databinding.PageritemHabitsBinding

class HabitsCardViewAdapter(
    private val onClick: (HabitsLocal) -> Unit
): RecyclerView.Adapter<HabitsCardViewAdapter.PagerViewHolder>() {

    private val habitMap = LinkedHashMap<PageType, HabitsLocal>()

    fun submitData(key: PageType, habit: HabitsLocal) {
        habitMap[key] = habit
        notifyDataSetChanged()
    }

    inner class PagerViewHolder internal constructor(var binding : PageritemHabitsBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(pageType : PageType,data : HabitsLocal){
                binding.pagerTvTitle.text = data.title
                binding.pagerTvStartTime.text = data.startTime
                binding.pagerTvMinutes.text = data.minuteFocus.toString()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            PageritemHabitsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val data = habitMap[key] ?: return
        holder.bind(key,data)
    }

    override fun getItemCount(): Int = habitMap.size

    private fun getIndexKey(position: Int) = habitMap.keys.toTypedArray().getOrNull(position)

    enum class PageType {
        HIGH, MEDIUM, LOW
    }

}