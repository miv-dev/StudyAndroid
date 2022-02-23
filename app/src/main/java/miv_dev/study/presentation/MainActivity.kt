package miv_dev.study.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import miv_dev.study.presentation.screens.MainScreen
import miv_dev.study.presentation.theme.StudyTheme
import miv_dev.study.presentation.view_models.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun HomePage() {
    val viewModel: MainViewModel = viewModel()

    Scaffold {
        Text(text = viewModel.uiState.isLogged.toString())
    }

}