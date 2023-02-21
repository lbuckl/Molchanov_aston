package com.molchanov.molchanov_lesson_2.ui.main

import android.os.Bundle
import android.util.Log
import androidx.viewbinding.ViewBinding
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseActivity
import com.molchanov.molchanov_lesson_2.ui.navigation.Router
import com.molchanov.molchanov_lesson_2.ui.main.pages.AstonMainFragment
import com.molchanov.molchanov_lesson_2.ui.splash.LoginFragment
import com.molchanov.molchanov_lesson_2.ui.main.pages.OfficesFragment
import com.molchanov.molchanov_lesson_2.ui.main.pages.VacancyFragment

class MainActivity: BaseActivity<ActivityMainBinding>() {

    private var router: Router? = null

    private val lastMenuItemSaveName = "Last_item_id"
    private var lastMenuItemId = -1

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastMenuItemId = savedInstanceState.getInt(lastMenuItemSaveName, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = getViewBinding()

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        initMenuListener()
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun addMainFragment() {
        router = Router(supportFragmentManager)
        router?.addFragment(
            binding.container.id,
            AstonMainFragment.instance,
            AstonMainFragment.FRAGMENT_TAG)
    }

    private fun initMenuListener(){
        binding.bnvMain.menu.let { menu ->

            binding.bnvMain.setOnItemReselectedListener { item ->
                when(item){
                    menu.findItem(R.id.bv_item_about_us) -> {
                        lastMenuItemId = R.id.bv_item_about_us
                        Log.v("@@@", "AstonMainFragment ${router == null}")
                        router?.replaceFragment(
                            binding.container.id,
                            AstonMainFragment.instance,
                            AstonMainFragment.FRAGMENT_TAG)
                    }
                    menu.findItem(R.id.bv_item_jobs) -> {
                        lastMenuItemId = R.id.bv_item_jobs
                        Log.v("@@@", "VacancyFragment ${router == null}")
                        router?.replaceFragment(
                            binding.container.id,
                            VacancyFragment.instance,
                            VacancyFragment.FRAGMENT_TAG
                        )
                    }
                    menu.findItem(R.id.bv_item_offices) -> {
                        lastMenuItemId = R.id.bv_item_offices
                        Log.v("@@@", "OfficesFragment ${router == null}")
                        router?.replaceFragment(
                            binding.container.id,
                            OfficesFragment.instance,
                            OfficesFragment.FRAGMENT_TAG
                        )
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(lastMenuItemSaveName, lastMenuItemId)
    }

    override fun onDestroy() {
        super.onDestroy()
        router = null
    }


}