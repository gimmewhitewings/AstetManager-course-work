package com.example.astetmanager.data.database.entities.enums

import com.example.astetmanager.R

enum class PartTypeClass {
    PILLOWCASE,
    SHEET,
    DUVET_COVER
}

fun PartTypeClass.getStringResourceId(): Int {
    return when (this) {
        PartTypeClass.PILLOWCASE -> R.string.pillowcases
        PartTypeClass.SHEET -> R.string.sheets
        PartTypeClass.DUVET_COVER -> R.string.duvet_covers
    }
}