package com.molchanov.molchanov_lesson_2.ui.main.offices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficesBinding
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.model.Repository
import com.molchanov.molchanov_lesson_2.ui.base.AstonViewModelFactory
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment
import com.molchanov.molchanov_lesson_2.ui.navigation.Router

/**
 * Фрагмент отображения списка офисов и информации о них
 */
class OfficesFragment : BaseFragment() {

    companion object {
        val instance = OfficesFragment()
        const val FRAGMENT_TAG = "OfficesFragment"
    }

    private lateinit var viewModel: OfficesViewModel

    private val onRVItemClickListener = object : OfficesRVAdapter.OnListItemClickListener {
        override fun onItemClick(data: OfficesInfo) {
            viewModel.setOfficeInfoData(data)
        }
    }

    private val rvAdapter = OfficesRVAdapter(onRVItemClickListener)

    lateinit var router: Router

    override val binding: FragmentOfficesBinding
        get() = _binding as FragmentOfficesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater, container, false)

        binding.rvOfficesList.adapter = rvAdapter

        router = Router(requireActivity().supportFragmentManager)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        val vmFactory = AstonViewModelFactory.also {
            it.setRepository(Repository(resources))
        }

        viewModel = ViewModelProvider(this, vmFactory)[OfficesViewModel::class.java]
        viewModel.getMyLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(state: OfficesAppState) {
        val bundle = Bundle()

        when (state) {
            is OfficesAppState.Success<*> -> {
                rvAdapter.replaceData(state.data as List<OfficesInfo>)
            }
            is OfficesAppState.ClickData -> {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        state.office.location,
                        state.office.address
                    )
                )

                replaceFragment(bundle)
            }
            is OfficesAppState.Error -> {
                Snackbar.make(binding.root, state.errorMsg, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun replaceFragment(bundle: Bundle) {
        router.replaceFragmentWithMessage(
            binding.officeContainer.id,
            OfficesSubFragment.instance,
            OfficesSubFragment.FRAGMENT_TAG,
            bundle
        )
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}