package com.molchanov.molchanov_lesson_2.ui.main.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficesBinding
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.model.Repository
import com.molchanov.molchanov_lesson_2.ui.base.AppState
import com.molchanov.molchanov_lesson_2.ui.base.AstonViewModelFactory
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment
import com.molchanov.molchanov_lesson_2.ui.main.MainActivity

/**
 * Фрагмент отображения списка офисов и информации о них
 */
class OfficesFragment : BaseFragment() {

    companion object {
        val instance = OfficesFragment()
        const val FRAGMENT_TAG = "OfficesFragment"
    }

   lateinit var viewModel: OfficesViewModel

    private val router = MainActivity.router

    override val binding: FragmentOfficesBinding
        get() = _binding as FragmentOfficesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater, container, false)

        initViewModel()

        initChips()

        return binding.root
    }

    private fun initViewModel(){
        val vmFactory = AstonViewModelFactory().also {
            it.setRepository(Repository(resources))
        }
        //viewModel = ViewModelProvider(this)[OfficesViewModel::class.java]
        viewModel = ViewModelProvider(this, vmFactory)[OfficesViewModel::class.java]
        viewModel.getMyLiveData().observe(viewLifecycleOwner){
            state -> renderData(state)
        }
    }

    private fun renderData(state: AppState){
        when(state){
            is AppState.Success<*> -> {
                Log.v("@@@","Success: ${state.data[0]}")
                //TODO
            }
            is AppState.Error -> {
                //TODO
            }
        }
    }

    private fun initChips(){
        with(binding){
            val bundle = Bundle()

            chipOfficesMoscow.setOnClickListener {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        resources.getString(R.string.moscow_header),
                        resources.getString(R.string.moscow_info)
                    )
                )

                replaceFragment(bundle)
            }

            chipOfficesKazan.setOnClickListener {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        resources.getString(R.string.kazan_header),
                        resources.getString(R.string.kazan_info)
                    )
                )

                replaceFragment(bundle)
            }

            chipOfficesRnd.setOnClickListener {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        resources.getString(R.string.rostov_on_don_header),
                        resources.getString(R.string.rostov_on_don_info)
                    )
                )

                replaceFragment(bundle)
            }

            chipOfficesMinsk.setOnClickListener {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        resources.getString(R.string.minsk_header),
                        resources.getString(R.string.minsk_info)
                    )
                )

                replaceFragment(bundle)
            }

            chipOfficesSpb.setOnClickListener {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        resources.getString(R.string.saint_petersburg_header),
                        resources.getString(R.string.saint_petersburg_info)
                    )
                )

                replaceFragment(bundle)
            }

            chipOfficesGomel.setOnClickListener {
                bundle.putStringArray(
                    OfficesSubFragment.BUNDLE_TAG,
                    arrayOf(
                        resources.getString(R.string.gomel_header),
                        resources.getString(R.string.gomel_info)
                    )
                )

                replaceFragment(bundle)
            }
        }
    }

    private fun replaceFragment(bundle: Bundle){
        router?.replaceFragmentWithMessage(
            binding.officeContainer.id,
            OfficesSubFragment.instance,
            OfficesSubFragment.FRAGMENT_TAG,
            bundle
        )
    }
}