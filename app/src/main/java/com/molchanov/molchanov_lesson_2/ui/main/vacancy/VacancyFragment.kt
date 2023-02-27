package com.molchanov.molchanov_lesson_2.ui.main.vacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.databinding.FragmentVacancyBinding
import com.molchanov.molchanov_lesson_2.domain.VacancyInfo
import com.molchanov.molchanov_lesson_2.model.Repository
import com.molchanov.molchanov_lesson_2.ui.base.AstonViewModelFactory
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment

/**
 * Фрагмент для отбражения текущийх вакансий
 */
class VacancyFragment : BaseFragment() {

    companion object {
        val instance = VacancyFragment()
        const val FRAGMENT_TAG = "VacancyFragment"
    }

    private lateinit var viewModel: VacancyViewModel

    private val rvAdapter = VacancyRVAdapter()

    override val binding: FragmentVacancyBinding
        get() = _binding as FragmentVacancyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)

        initViewModel()

        binding.rvVacancyList.adapter = rvAdapter

        initSearchInputLayout()

        return binding.root
    }

    private fun initViewModel() {
        val vmFactory = AstonViewModelFactory.also {
            it.setRepository(Repository(resources))
        }

        viewModel = ViewModelProvider(this, vmFactory)[VacancyViewModel::class.java]

        viewModel.getMyLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(state: VacancyAppState) {
        when (state) {
            is VacancyAppState.Success<*> -> {
                rvAdapter.differ.submitList(state.data as List<VacancyInfo>)
            }
            is VacancyAppState.Error -> {
                Snackbar.make(binding.root, state.errorMsg, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initSearchInputLayout() {
        binding.tilVacancySearch.setEndIconOnClickListener {

            binding.etSearchText.text.toString().let { txt ->

                viewModel.getFilteredData(
                    txt
                )
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}