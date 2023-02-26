package com.molchanov.molchanov_lesson_2.ui.main.vacancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanov_lesson_2.databinding.FragmentVacancyRvItemBinding
import com.molchanov.molchanov_lesson_2.domain.VacancyInfo

/**
 * Адаптер списка вакансий компании ASTON
 */
class VacancyRVAdapter : RecyclerView.Adapter<VacancyRVAdapter.ViewHolder>() {

    private var vacancyList: List<VacancyInfo> = listOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: VacancyInfo) {
            val binding = FragmentVacancyRvItemBinding.bind(itemView)
            binding.tvVacancyHeader.text = data.title
            binding.tvVacancyContent.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentVacancyRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vacancyList[position])
    }

    override fun getItemCount(): Int {
        return vacancyList.size
    }

    fun replaceData(newOfficeList: List<VacancyInfo>) {
        vacancyList = newOfficeList
        notifyDataSetChanged()
    }
}