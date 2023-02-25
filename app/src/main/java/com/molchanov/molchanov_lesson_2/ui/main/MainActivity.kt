package com.molchanov.molchanov_lesson_2.ui.main

import android.os.Bundle
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseActivity
import com.molchanov.molchanov_lesson_2.ui.main.pages.AstonMainFragment
import com.molchanov.molchanov_lesson_2.ui.main.pages.OfficesFragment
import com.molchanov.molchanov_lesson_2.ui.main.pages.VacancyFragment
import com.molchanov.molchanov_lesson_2.ui.navigation.Router

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        var router: Router? = null
    }

    private val lastMenuItemSaveName = "Last_item_id"

    private var lastMenuItemId = -1

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        lastMenuItemId = savedInstanceState.getInt(lastMenuItemSaveName, -1)

        router = Router(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = getViewBinding()

        router = Router(supportFragmentManager)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        initMenuListener()
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun addMainFragment() {

        router?.addFragment(
            binding.container.id,
            AstonMainFragment.instance,
            AstonMainFragment.FRAGMENT_TAG
        )

        this.supportActionBar?.title = resources.getString(R.string.about_us)
    }

    /**
     * Инициализация BottomNavigationView
     */
    private fun initMenuListener() {
        binding.bnvMain.menu.let { menu ->

            binding.bnvMain.setOnItemSelectedListener { item ->
                when (item) {
                    menu.findItem(R.id.bv_item_about_us) -> {
                        lastMenuItemId = R.id.bv_item_about_us

                        router?.replaceFragment(
                            binding.container.id,
                            AstonMainFragment.instance,
                            AstonMainFragment.FRAGMENT_TAG
                        )

                        this.supportActionBar?.title = resources.getString(R.string.about_us)
                    }
                    menu.findItem(R.id.bv_item_jobs) -> {
                        lastMenuItemId = R.id.bv_item_jobs

                        router?.replaceFragment(
                            binding.container.id,
                            VacancyFragment.instance,
                            VacancyFragment.FRAGMENT_TAG
                        )

                        this.supportActionBar?.title = resources.getString(R.string.jobs)
                    }
                    menu.findItem(R.id.bv_item_offices) -> {
                        lastMenuItemId = R.id.bv_item_offices

                        router?.replaceFragment(
                            binding.container.id,
                            OfficesFragment.instance,
                            OfficesFragment.FRAGMENT_TAG
                        )

                        this.supportActionBar?.title = resources.getString(R.string.offices)
                    }
                }

                return@setOnItemSelectedListener true
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