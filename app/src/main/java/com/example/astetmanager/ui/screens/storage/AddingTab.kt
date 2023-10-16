package com.example.astetmanager.ui.screens.storage

import com.example.astetmanager.R

enum class AddingTab {
    PART_TYPE,
    COMPLECT
}

fun AddingTab.getStringResourceId(): Int {
    return when (this) {
        AddingTab.PART_TYPE -> R.string.part_type
        AddingTab.COMPLECT -> R.string.complect
    }
}