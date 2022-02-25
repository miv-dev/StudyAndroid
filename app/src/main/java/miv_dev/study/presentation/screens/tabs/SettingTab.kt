package miv_dev.study.presentation.screens.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import miv_dev.study.presentation.components.ForwardButton
import miv_dev.study.presentation.components.SettingItem


val SettingsColors = listOf(
    Color(254,52,110),
    Color(255, 189, 105),
    Color(192, 96, 161),
    Color(254,52,110),
    Color(254,52,110),

    )

@Composable
fun SettingsTab() {
    Column(Modifier.padding(16.dp)) {
        Text("Account", style = MaterialTheme.typography.h5 )
        Spacer(Modifier.height(16.dp))
        SettingItem(
            Icons.Rounded.Login,
            SettingsColors[0],
            "Login"
        ){
            ForwardButton {

            }
        }
        Spacer(Modifier.height(32.dp))
        Text("Settings", style = MaterialTheme.typography.h5 )
        Spacer(Modifier.height(16.dp))
        SettingItem(
            Icons.Rounded.DarkMode,
            SettingsColors[1],
            "Dark Mode"
        ){
            ForwardButton {

            }
        }
    }
}