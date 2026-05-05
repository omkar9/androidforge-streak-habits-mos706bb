package com.androidforge.streakhappits.domain.model

import java.time.LocalDate

data class CompletionRecord(
    val id: Long = 0,
    val habitId: Long,
    val completionDate: LocalDate
)