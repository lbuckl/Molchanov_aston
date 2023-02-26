package com.molchanov.molchanov_lesson_2.ui.main.offices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficeRvItemBinding
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo

/**
 * Адаптер списка оффисов компании ASTON
 */
class OfficesRVAdapter(
    private val callback: OnListItemClickListener
) :
    RecyclerView.Adapter<OfficesRVAdapter.ViewHolder>() {

    private var officeList: List<OfficesInfo> = listOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: OfficesInfo) {
            val binding = FragmentOfficeRvItemBinding.bind(itemView)
            binding.tvHeader.text = data.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentOfficeRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(officeList[position])

        holder.itemView.setOnClickListener {
            callback.onItemClick(officeList[position])
        }
    }

    override fun getItemCount(): Int {
        return officeList.size
    }

    fun replaceData(newOfficeList: List<OfficesInfo>) {
        officeList = newOfficeList
        notifyDataSetChanged()
    }

    interface OnListItemClickListener {
        fun onItemClick(data: OfficesInfo)
    }
}