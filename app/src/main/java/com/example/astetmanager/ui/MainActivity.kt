package com.example.astetmanager.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.astetmanager.ui.screens.new_order.NewOrderScreen
import com.example.astetmanager.ui.screens.new_order.NewOrderViewModel
import com.example.astetmanager.ui.screens.orders.OrdersScreen
import com.example.astetmanager.ui.screens.orders.OrdersViewModel
import com.example.astetmanager.ui.screens.home.HomeScreen
import com.example.astetmanager.ui.screens.home.HomeViewModel
import com.example.astetmanager.ui.screens.schedule.ScheduleScreen
import com.example.astetmanager.ui.screens.schedule.ScheduleViewModel
import com.example.astetmanager.ui.screens.storage.StorageScreen
import com.example.astetmanager.ui.screens.storage.StorageViewModel
import com.example.astetmanager.ui.theme.AstetManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomBarScreens = listOf(
            Screen.BottomBarScreen.Home,
            Screen.BottomBarScreen.Schedule,
            Screen.BottomBarScreen.Storage,
            Screen.BottomBarScreen.Orders
        )

        setContent {
            AstetManagerTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    bottomBar = {
                        if (bottomBarScreens.any { currentDestination?.route == it.route }) {
                            NavigationBar {
                                bottomBarScreens.forEach { screen ->
                                    val isSelected =
                                        currentDestination?.hierarchy?.any { it.route == screen.route }
                                            ?: false
                                    NavigationBarItem(
                                        selected = isSelected,
                                        onClick = { navController.navigate(screen.route) },
                                        alwaysShowLabel = false,
                                        icon = {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(
                                                    id = if (isSelected) {
                                                        screen.selectedIconResourceId
                                                    } else {
                                                        screen.unselectedIconResourceId
                                                    }
                                                ),
                                                contentDescription = null
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(id = screen.stringResourceId),
                                                maxLines = 1,
                                                fontSize = 10.sp
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screen.BottomBarScreen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        bottomBarScreens(navController)


                        composable(Screen.NewOrder.route) {
                            val newOrderViewModel = hiltViewModel<NewOrderViewModel>()
                            NewOrderScreen(
                                navController = navController,
                                viewModel = newOrderViewModel
                            )
                        }
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.bottomBarScreens(navController: NavHostController) {
        composable(Screen.BottomBarScreen.Home.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel
            )
        }

        composable(Screen.BottomBarScreen.Schedule.route) {
            val scheduleViewModel = hiltViewModel<ScheduleViewModel>()
            ScheduleScreen(
                navController = navController,
                viewModel = scheduleViewModel
            )
        }

        composable(Screen.BottomBarScreen.Storage.route) {
            val storageViewModel = hiltViewModel<StorageViewModel>()
            StorageScreen(
                viewModel = storageViewModel
            )
        }

        composable(Screen.BottomBarScreen.Orders.route) {
            val ordersViewModel = hiltViewModel<OrdersViewModel>()
            OrdersScreen(
                navController = navController,
                viewModel = ordersViewModel
            )
        }
    }
}
