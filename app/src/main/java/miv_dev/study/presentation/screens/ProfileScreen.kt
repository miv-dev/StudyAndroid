package miv_dev.study.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import miv_dev.study.presentation.components.ForwardButton
import miv_dev.study.presentation.components.SettingItem
import miv_dev.study.presentation.view_models.UserViewModel

val SettingsColors = listOf(
    Color(254, 52, 110),
    Color(255, 189, 105),
    Color(192, 96, 161),
    Color(254, 52, 110),
    Color(254, 52, 110),

    )

@Composable
fun ProfileScreen(navController: NavController, userViewModel: UserViewModel = hiltViewModel()) {
    val userState = userViewModel.userState
    LaunchedEffect(Unit) {
        if (userState.user == null) {
            navController.popBackStack()
        }
    }
    val user = userState.user

    if (user == null) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = Color.Transparent,
                    title = { Text("Profile") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Column(Modifier.padding(horizontal = 16.dp)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .requiredSize(100.dp)
                            .border(
                                2.dp,
                                color = MaterialTheme.colors.primaryVariant.copy(0.4f),
                                shape = MaterialTheme.shapes.large
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    color = MaterialTheme.colors.primaryVariant,
                                    shape = MaterialTheme.shapes.large
                                )
                                .align(Alignment.BottomEnd)
                        ) {
                            Icon(
                                Icons.Rounded.Add,
                                contentDescription = "edit",
                                tint = MaterialTheme.colors.background,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }


                    }
                }
                Text(user.name, modifier = Modifier.align(CenterHorizontally))
                Spacer(Modifier.height(16.dp))
                Divider()
                Spacer(Modifier.height(16.dp))
                SettingItem(
                    leadingIcon = Icons.Rounded.AccountCircle,
                    leadingIconColor = SettingsColors[0],
                    title = "Personal Info"
                ) {
                    ForwardButton {
                        navController.navigate("edit")
                    }
                }
                SettingItem(
                    leadingIcon = Icons.Rounded.Notifications,
                    leadingIconColor = SettingsColors[1],
                    title = "Notifications"
                ) {
                    ForwardButton {
                    }
                }

                Spacer(Modifier.height(16.dp))
                Divider()
                Spacer(Modifier.height(16.dp))

                SettingItem(
                    leadingIcon = Icons.Rounded.Logout,
                    leadingIconColor = SettingsColors[2],
                    title = "Logout"
                ) {
                    ForwardButton {
                        userViewModel.logout()
                        navController.popBackStack()
                    }
                }
            }
        }
    }


}