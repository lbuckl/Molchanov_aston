package com.molchanov.molchanov_lesson_2.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficeRvItemBinding
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.loadImageFromUrl

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: OfficesInfo)
}

//region ВьюХолдеры для OfficeFragment
class RuViewHolder(view: View) : BaseViewHolder(view) {
    override fun bind(data: OfficesInfo) {
        val binding = FragmentOfficeRvItemBinding.bind(itemView)
        binding.tvHeader.text = data.location
        binding.ivAstonIcon.loadImageFromUrl(R.drawable.ic_russia)
    }
}

class ByViewHolder(view: View) : BaseViewHolder(view) {
    override fun bind(data: OfficesInfo) {
        val binding = FragmentOfficeRvItemBinding.bind(itemView)
        binding.tvHeader.text = data.location
        binding.ivAstonIcon.loadImageFromUrl(R.drawable.ic_belarus)
    }
}
//endregion