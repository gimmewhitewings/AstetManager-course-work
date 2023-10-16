package com.example.astetmanager

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Screen(
    open val route: String,
    @StringRes open val stringResourceId: Int
) {

    sealed class BottomBarScreen(
        override val route: String,
        @StringRes override val stringResourceId: Int,
        @DrawableRes val unselectedIconResourceId: Int,
        @DrawableRes val selectedIconResourceId: Int
    ) : Screen(route, stringResourceId) {

        data object Home : BottomBarScreen(
            route = "home",
            stringResourceId = R.string.home,
            unselectedIconResourceId = R.drawable.ic_home,
            selectedIconResourceId = R.drawable.ic_home_filled
        )

        data object Schedule : BottomBarScreen(
            route = "schedule",
            stringResourceId = R.string.schedule,
            unselectedIconResourceId = R.drawable.ic_calendar,
            selectedIconResourceId = R.drawable.ic_calendar_filled
        )

        data object Storage : BottomBarScreen(
            route = "storage",
            stringResourceId = R.string.storage,
            unselectedIconResourceId = R.drawable.ic_storage,
            selectedIconResourceId = R.drawable.ic_storage_filled
        )

        data object Documents : BottomBarScreen(
            route = "documents",
            stringResourceId = R.string.documents,
            unselectedIconResourceId = R.drawable.ic_document,
            selectedIconResourceId = R.drawable.ic_document_filled
        )
    }

    data object Complect : Screen(
        route = "complect",
        stringResourceId = R.string.complect
    )

    data object Cloth : Screen(
        route = "cloth",
        stringResourceId = R.string.cloth
    )

    data object Application : Screen(
        route = "application",
        stringResourceId = R.string.application
    )

    data object Implementation : Screen(
        route = "implementation",
        stringResourceId = R.string.implementation
    )
}
