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

        data object Home : BottomBarScreen(
            route = "home",
            resourceId = R.string.home,
            iconResourceId = R.drawable.ic_home,
            selectedIconResourceId = R.drawable.ic_home_filled
        )

        data object Schedule : BottomBarScreen(
            route = "schedule",
            resourceId = R.string.schedule,
            iconResourceId = R.drawable.ic_calendar,
            selectedIconResourceId = R.drawable.ic_calendar_filled
        )

        data object Storage : BottomBarScreen(
            route = "storage",
            resourceId = R.string.storage,
            iconResourceId = R.drawable.ic_storage,
            selectedIconResourceId = R.drawable.ic_storage_filled
        )

        data object Documents : BottomBarScreen(
            route = "documents",
            resourceId = R.string.documents,
            iconResourceId = R.drawable.ic_document,
            selectedIconResourceId = R.drawable.ic_document_filled
        )
    }

    data object Complect : Screen(
        route = "complect",
        resourceId = R.string.complect
    )

    data object Cloth : Screen(
        route = "cloth",
        resourceId = R.string.cloth
    )

    data object Application : Screen(
        route = "application",
        resourceId = R.string.application
    )

    data object Implementation : Screen(
        route = "implementation",
        resourceId = R.string.implementation
    )
}
