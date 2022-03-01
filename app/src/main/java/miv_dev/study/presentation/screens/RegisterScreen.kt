package miv_dev.study.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import miv_dev.study.presentation.view_models.LoadingState
import miv_dev.study.presentation.view_models.UserViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {


    val formState = viewModel.formState

    LaunchedEffect(formState.loadingState) {
        if (formState.loadingState == LoadingState.Loaded) {
            viewModel.clearFormState()
            navController.popBackStack(route = "main", inclusive = false)
        }
    }

    Scaffold(topBar = {
        TopAppBar(elevation = 0.dp,
            backgroundColor = Color.Transparent,
            title = { Text("Register") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back"
                    )
                }
            })
    }) { innerPadding ->
        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
            Column(
                Modifier
                    .padding(
                        bottom = innerPadding.calculateBottomPadding(),
                        end = 16.dp,
                        start = 16.dp
                    )
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(1f))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = MaterialTheme.shapes.medium,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        autoCorrect = false
                    ),
                    isError = formState.emailError.isNotEmpty(),
                    value = formState.email,
                    onValueChange = { formState.email = it },
                    label = { Text("Email") }
                )
                if (formState.emailError.isNotEmpty()) {
                    Text(
                        text = formState.emailError,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.Start)

                    )
                }
                Spacer(Modifier.requiredHeight(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    shape = MaterialTheme.shapes.medium,
                    value = formState.password,
                    isError = formState.passwordError.isNotEmpty(),

                    onValueChange = { formState.password = it },
                    label = { Text("Password") },
                )
                if (formState.passwordError.isNotEmpty()) {
                    Text(
                        text = formState.passwordError,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.Start)
                    )
                }
                Spacer(Modifier.weight(1f))

                Button(
                    onClick = { viewModel.register() },
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(horizontal = 80.dp, vertical = 12.dp)
                ) {
                    if (formState.loadingState == LoadingState.Loading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    }else{
                        Text("Register")
                    }
                }
                Spacer(Modifier.weight(0.2f))
                Divider()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    SocialButton(
                        onTap = { /*TODO*/ },
                        iconId = SocialIcons.Google,
                        Color(209, 78, 62, 200)
                    )
                    SocialButton(
                        onTap = { /*TODO*/ },
                        iconId = SocialIcons.Facebook,
                        Color(73, 93, 148, 200)
                    )
                    SocialButton(
                        onTap = { /*TODO*/ },
                        iconId = SocialIcons.Windows,
                        Color(127, 188, 0, 200)
                    )
                }
            }
        }
    }
}