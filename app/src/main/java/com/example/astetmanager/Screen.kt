package com.example.astetmanager

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Screen(
    open val route: String,
    @StringRes open val resourceId: Int
) {

    sealed class BottomBarScreen(
        override val route: String,
        @StringRes override val resourceId: Int,
        @DrawableRes val iconResourceId: Int,
        @DrawableRes val selectedIconResourceId: Int
    ) : Screen(route, resourceId) {

        object Home : BottomBarScreen(
            route = "home",
            resourceId = R.string.home,
            iconResourceId = R.drawable.ic_home,
            selectedIconResourceId = R.drawable.ic_home_filled
        )

        object Schedule : BottomBarScreen(
            route = "schedule",
            resourceId = R.string.schedule,
            iconResourceId = R.drawable.ic_calendar,
            selectedIconResourceId = R.drawable.ic_calendar_filled
        )

        object Storage : BottomBarScreen(
            route = "storage",
            resourceId = R.string.storage,
            iconResourceId = R.drawable.ic_storage,
            selectedIconResourceId = R.drawable.ic_storage_filled
        )

        object Documents : BottomBarScreen(
            route = "documents",
            resourceId = R.string.documents,
            iconResourceId = R.drawable.ic_document,
            selectedIconResourceId = R.drawable.ic_document_filled
        )
    }

    object Complect : Screen(
        route = "complect",
        resourceId = R.string.complect
    )
}
