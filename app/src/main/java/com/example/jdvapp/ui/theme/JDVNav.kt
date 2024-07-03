package com.example.jdvapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
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
sealed class Routes(val route: String) {
    // Route 1
     object HomeScreen : Routes("HomeScreen")
    //Route 2
     object GridScreen : Routes("GridScreen")
    //Route 5
     object LogScreen : Routes("LogScreen")
    //Route 6
     object RulesScreen : Routes("RulesScreen")
    //Route 3 avec paramètre
     object SaveScreen : Routes("SaveScreen/{id}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètres
        fun withObject(data: UserBean) = "SaveScreen/${data.id}"
    }
}
@Composable
fun AppNavigation(viewModel: JDVViewModel) {

    val navHostController: NavHostController = rememberNavController()

    //viewModel appartient au framework permet de récupérer une instance déjà existante s'il en existe une
//Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.GridScreen.route) {
        composable(Routes.GridScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            GridScreen(navHostController,JDVViewModel())
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
    }
}




