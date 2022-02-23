package miv_dev.study.presentation.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import miv_dev.study.presentation.view_models.MainViewModel

val titles = listOf("Home", "Exams", "Settings")

@Composable
fun MainScreen() {


    val viewModel: MainViewModel = viewModel()
    val uiState = viewModel.uiState
    var selectedItem by remember { mutableStateOf(0) }
    val icons = Icons.Rounded
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                BottomNavigationItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    label = { Text(titles[0]) },
                    alwaysShowLabel = selectedItem == 0,
                    icon = {
                        Icon(
                            icons.Home, contentDescription = null
                        )
                    })
                BottomNavigationItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    label = { Text(titles[1]) },
                    alwaysShowLabel = selectedItem == 1,
                    icon = {
                        Icon(
                            icons.MenuBook, contentDescription = null
                        )
                    })
                BottomNavigationItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    label = { Text(titles[0]) },
                    alwaysShowLabel = selectedItem == 2,
                    icon = {
                        Icon(
                            icons.Tune,
                            contentDescription = null
                        )
                    })
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(titles[selectedItem]) },
                actions = {
                    val userIcon = if (uiState.isLogged) icons.Person else icons.Login
                    if (uiState.isLogged){
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = icons.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }

                    IconButton(onClick = {}) {
                        Icon(imageVector = userIcon, contentDescription = "User")
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        }
    ) {
    }
}