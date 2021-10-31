package dev.linmg.codigo_ui_test.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.linmg.codigo_ui_test.first.FirstScreen
import dev.linmg.codigo_ui_test.second.SecondScreen
import dev.linmg.codigo_ui_test.third.ThirdScreen


@ExperimentalPagerApi
@Composable
fun router() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.FirstScreen.route) {
        composable(route = Screens.FirstScreen.route) {
            FirstScreen(navController)
        }
        composable(
            route = Screens.SecondScreen.route,

            ) {
            SecondScreen(navController)
        }
        composable(
            route = Screens.ThirdScreen.route.plus("/{car_name}/{car_type}"),
            arguments = listOf(
                navArgument("car_name") {
                    type = NavType.StringType
                    defaultValue="Car Name"
                },
                navArgument("car_type") {
                    type = NavType.StringType
                    defaultValue="Car Type"
                }
            ),
        ) {
            ThirdScreen(
                navController,
                it.arguments?.getString("car_name"),
                it.arguments?.getString("car_type")
            )
        }
    }
}

