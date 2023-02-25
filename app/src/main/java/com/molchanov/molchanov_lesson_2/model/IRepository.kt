package com.molchanov.molchanov_lesson_2.model

import com.molchanov.molchanov_lesson_2.domain.AstonInfo
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.domain.VacancyInfo

interface IRepository {

    fun getAstonInfo(): AstonInfo

    fun getVacancyInfo(): List<VacancyInfo>

    fun getOfficesInfo(): List<OfficesInfo>
}