package miv_dev.study.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import miv_dev.study.presentation.components.InternetErrorImage
import miv_dev.study.presentation.screens.tabs.ExamsTab
import miv_dev.study.presentation.screens.tabs.HomeTab
import miv_dev.study.presentation.screens.tabs.SettingsTab
import miv_dev.study.presentation.view_models.MainViewModel
import miv_dev.study.presentation.view_models.UserViewModel

val titles = listOf("Home", "Exams", "Settings")
var selectedItem by mutableStateOf(0)

@Composable
fun MainScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val viewModel: MainViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val uiState = viewModel.uiState
    val userState = userViewModel.userState
    val icons = Icons.Rounded
    val scope = rememberCoroutineScope()
    val theme = MaterialTheme.colors

    LaunchedEffect(uiState.hasInternetConnection) {
        if (!uiState.hasInternetConnection) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Check Internet Connection",
                    duration = SnackbarDuration.Indefinite,
                )
            }
        } else {
            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = theme.error,
                    contentColor = theme.onError,
                    snackbarData = data
                )
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(backgroundColor = theme.background) {
                BottomNavigationItem(
                    unselectedContentColor = theme.onBackground.copy(0.5f),
                    selectedContentColor = theme.primary,
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
                    unselectedContentColor = theme.onBackground.copy(0.5f),
                    selectedContentColor = theme.primary,
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
                    unselectedContentColor = theme.onBackground.copy(0.5f),
                    selectedContentColor = theme.primary,
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
            if (selectedItem != 2) {
                TopAppBar(
                    title = { Text(titles[selectedItem]) },
                    actions = {
                        val userIcon = if (userState.isLogged) icons.Person else icons.Login
                        Log.d("UI", "MainScreen: ${userState.isLogged} ")
                        if (userState.isLogged) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = icons.Notifications,
                                    contentDescription = "Notifications"
                                )
                            }
                        }

                        IconButton(onClick = {
                            if (!userState.isLogged) {
                                navController.navigate("login")
                            } else {
                                navController.navigate("profile")
                            }
                        }) {
                            Icon(imageVector = userIcon, contentDescription = "User")
                        }
                    },
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                )
            }
        }
    ) {
        Box(Modifier.padding(bottom = it.calculateBottomPadding())) {
            if (!uiState.hasInternetConnection && selectedItem in 0..1) {
                InternetErrorImage()
            } else {
                when (selectedItem) {
                    1 -> ExamsTab()
                    2 -> SettingsTab()
                    else -> HomeTab()
                }
            }
        }
    }

}