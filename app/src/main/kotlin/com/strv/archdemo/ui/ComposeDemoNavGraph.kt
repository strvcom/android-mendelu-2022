package com.strv.archdemo.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.strv.archdemo.ui.counter.CounterScreen
import com.strv.archdemo.ui.counter.CounterViewModel
import com.strv.archdemo.ui.main.MainScreen

@Composable
fun ComposeDemoNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ComposeDemoDestinations.MAIN_ROUTE
    ) {
        composable(ComposeDemoDestinations.MAIN_ROUTE) {
            val counterTitle = "Hello World!"
            MainScreen(
                goToCounterClick = {
                    navController.navigate("${ComposeDemoDestinations.COUNTER_ROUTE}/$counterTitle")
                }
            )
        }
        composable(
            route = "${ComposeDemoDestinations.COUNTER_ROUTE}/{${ComposeDemoDestinations.COUNTER_TITLE_KEY}}",
            arguments = listOf(
                navArgument(ComposeDemoDestinations.COUNTER_TITLE_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val counterViewModel = hiltViewModel<CounterViewModel>()

            CounterScreen(
                { navController.popBackStack() },
                counterViewModel
            )
        }
    }
}