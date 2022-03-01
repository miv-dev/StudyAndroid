package miv_dev.study.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import miv_dev.study.presentation.screens.*
import miv_dev.study.presentation.theme.StudyTheme
import miv_dev.study.presentation.view_models.UserViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudyTheme {
                RootLayer()
            }
        }
    }
}

@Composable
fun RootLayer() {
    val navController = rememberNavController()
    val userViewModel = hiltViewModel<UserViewModel>()
    Scaffold {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {  MainScreen(navController, userViewModel) }
            composable("login") { LoginScreen(navController, userViewModel) }
            composable("profile") { ProfileScreen(navController, userViewModel) }
            composable("register") { RegisterScreen(navController, userViewModel) }
            composable("edit") { EditProfileScreen(navController, userViewModel) }
        }
    }
}