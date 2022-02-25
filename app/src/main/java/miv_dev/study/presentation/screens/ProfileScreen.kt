package miv_dev.study.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import miv_dev.study.presentation.components.ForwardButton
import miv_dev.study.presentation.components.SettingItem

val SettingsColors = listOf(
    Color(254, 52, 110),
    Color(255, 189, 105),
    Color(192, 96, 161),
    Color(254, 52, 110),
    Color(254, 52, 110),

    )

@Composable
fun ProfileScreen(navController: NavController) {
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
            SettingItem(
                leadingIcon = Icons.Rounded.Logout,
                leadingIconColor = SettingsColors[0],
                title = "Logout"
            ) {
                ForwardButton {

                }
            }
        }
    }
}