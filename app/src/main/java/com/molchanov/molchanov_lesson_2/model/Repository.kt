package com.molchanov.molchanov_lesson_2.model

import android.content.res.Resources
import android.util.Log
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.domain.AstonInfo
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.domain.VacancyInfo


class Repository(private val resources: Resources) : IRepository {

    override fun getAstonInfo(): AstonInfo {
        with(resources) {
            return AstonInfo(
                getString(R.string.tv_aston_card_primary),
                getString(R.string.tv_aston_card_secondary),
                getString(R.string.about_us),
                getString(R.string.about_us_description)
            )
        }
    }

    override fun getVacancyInfo(): List<VacancyInfo> {

        val result = mutableListOf<VacancyInfo>()
        val buf = resources.getStringArray(R.array.vacancy_array)

        for (i in buf.indices step 2) {
            result.add(
                VacancyInfo(
                    buf[i],
                    buf[i + 1]
                )
            )
        }

        return result.toList()

    }

    override fun getOfficesInfo(): List<OfficesInfo> {

        val result = mutableListOf<OfficesInfo>()
        val buf = resources.getStringArray(R.array.offices_location_array)

        for (i in buf.indices step 2) {
            result.add(
                OfficesInfo(
                    buf[i],
                    buf[i + 1],
                )
            )
        }
        return result.toList()

    }
}