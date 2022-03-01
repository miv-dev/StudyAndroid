package miv_dev.study.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import miv_dev.study.presentation.view_models.UserViewModel

@Composable
fun EditProfileScreen(
    navController: NavController, userViewModel: UserViewModel = hiltViewModel()
) {
    var name by remember {
        mutableStateOf("")
    }
    val user = userViewModel.userState.user
    LaunchedEffect(Unit) {
        if (user != null) {
            name = user.name
        }
    }
    Scaffold(topBar = {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            title = { Text("Edit") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {
        Column {
            OutlinedTextField(value = name, onValueChange = { name = it })
            Spacer(Modifier.weight(1f))
            Box(Modifier.height(56.dp)) {
                Button(onClick = {
                    if (user != null) {
                        userViewModel.update(user.copy(name = name))
                    }
                }) {
                    Text("Submit")
                }
            }
        }
    }
}