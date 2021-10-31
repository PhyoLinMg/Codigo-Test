package dev.linmg.codigo_ui_test.ui.route

sealed class Screens(val route:String){
    object FirstScreen: Screens("main_screen")
    object SecondScreen: Screens("second_screen")
    object ThirdScreen: Screens("third_screen")
}
