package miv_dev.study.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import miv_dev.study.presentation.screens.LoginScreen
import miv_dev.study.presentation.screens.MainScreen
import miv_dev.study.presentation.screens.ProfileScreen
import miv_dev.study.presentation.theme.StudyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            StudyTheme {
                Scaffold{
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("profile") { ProfileScreen(navController) }
                    }
                }

            }
        }
    }
}