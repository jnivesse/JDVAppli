package com.example.jdvapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jdvapp.ui.theme.model.JDVViewModel
import com.example.jdvapp.ui.theme.model.UserBean
import com.example.jdvapp.ui.theme.screen.GridScreen
import com.example.jdvapp.ui.theme.screen.LogScreen
import com.example.jdvapp.ui.theme.screen.RulesScreen
import com.example.jdvapp.ui.theme.screen.SaveScreen

sealed class Routes(val route: String) {
    object HomeScreen : Routes("HomeScreen")
    object GridScreen : Routes("GridScreen")
    object LogScreen : Routes("LogScreen")
    object RulesScreen : Routes("RulesScreen")
    object SaveScreen : Routes("SaveScreen/{id}") {
        fun withObject(data: UserBean) = "SaveScreen/${data.id}"
    }
}

@Composable
fun AppNavigation() {
    val navHostController: NavHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Routes.GridScreen.route) {
        composable(Routes.HomeScreen.route) {
            GridScreen(navHostController, JDVViewModel())
        }

        composable(Routes.LogScreen.route) {
            LogScreen(navHostController, JDVViewModel())
        }

        composable(Routes.RulesScreen.route) {
            RulesScreen(navHostController, JDVViewModel())
        }

        composable(
            route = Routes.SaveScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            val idNav = it.arguments?.getLong("id") ?: 10
            SaveScreen(navHostController, JDVViewModel())
        }
    }
}
