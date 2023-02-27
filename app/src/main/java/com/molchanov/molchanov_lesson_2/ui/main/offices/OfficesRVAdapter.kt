package com.molchanov.molchanov_lesson_2.ui.main.offices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficeRvItemBinding
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficeRvItemByBinding
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficeRvItemRuBinding
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.findWordInText
import com.molchanov.molchanov_lesson_2.ui.base.BaseViewHolder
import com.molchanov.molchanov_lesson_2.ui.base.ByViewHolder
import com.molchanov.molchanov_lesson_2.ui.base.DefaultViewHolder
import com.molchanov.molchanov_lesson_2.ui.base.RuViewHolder

/**
 * Адаптер списка оффисов компании ASTON
 */
class OfficesRVAdapter(
    private val callback: OnListItemClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object{
        private const val TYPE_DEFAULT = 0
        private const val TYPE_RUSSIAN = 1
        private const val TYPE_BELARUS = 2
    }

    private var officeList: List<OfficesInfo> = listOf()

    override fun getItemViewType(position: Int): Int {
        return setTypeFromOfficeName(officeList[position].location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when(viewType){
            TYPE_DEFAULT -> {
                val binding = FragmentOfficeRvItemBinding.inflate(LayoutInflater.from(parent.context))
                return DefaultViewHolder(binding.root)
            }
            TYPE_RUSSIAN -> {
                val binding = FragmentOfficeRvItemRuBinding.inflate(LayoutInflater.from(parent.context))
                return RuViewHolder(binding.root)
            }
            TYPE_BELARUS -> {
                val binding = FragmentOfficeRvItemByBinding.inflate(LayoutInflater.from(parent.context))
                return ByViewHolder(binding.root)
            }
            else -> {
                val binding = FragmentOfficeRvItemBinding.inflate(LayoutInflater.from(parent.context))
                return DefaultViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
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

    private fun setTypeFromOfficeName(text: String): Int{
        if (findWordInText(text,"Россия") || findWordInText(text,"Russia"))
            return TYPE_RUSSIAN

        if (findWordInText(text,"Беларусь") || findWordInText(text,"Belarus"))
            return TYPE_BELARUS

        return TYPE_DEFAULT
    }
}