package com.example.jdvapp.ui.theme.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jdvapp.ui.theme.model.JDVViewModel
import com.example.jdvapp.ui.theme.model.UserBean

sealed class Routes(val route: String) {

    // Route 1

    data object HomeScreen : Routes("HomeScreen")
    //Route 2
    data object GridScreen : Routes("GridScreen")
    //Route 5
    data object LogScreen : Routes("LogScreen")
    //Route 6
    data object RulesScreen : Routes("RulesScreen")
    //Route 3 avec paramètre
    data object SaveScreen : Routes("SaveScreen/{id}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètres
        fun withObject(data: UserBean) = "SaveScreen/${data.id}"
    }
}
@Composable
fun AppNavigation() {

    val navHostController: NavHostController = rememberNavController()

    //viewModel appartient au framework permet de récupérer une instance déjà existante s'il en existe une
    val myMatchViewModel: JDVViewModel = viewModel()
//Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.GridScreen.route) {
        composable(Routes.HomeScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            GridScreen(navHostController)
        }

        //Route 1 vers notre LogScreen
        composable(Routes.LogScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            LogScreen(navHostController, JDVViewModel = JDVViewModel())
        }
        //Route 2 vers notre RulesScreen
        composable(Routes.RulesScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            RulesScreen(navHostController, JDVViewModel = JDVViewModel())
        }
        //Route 2 vers un liste des save
        composable(
            route = Routes.SaveScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            val idNav = it.arguments?.getLong("id") ?: 10
            SaveScreen(navHostController,  JDVViewModel = JDVViewModel())
        }
    }


}




