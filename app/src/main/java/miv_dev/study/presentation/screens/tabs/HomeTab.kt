package miv_dev.study.presentation.screens.tabs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import miv_dev.study.presentation.view_models.HomeViewModel

@Composable
fun HomeTab() {
    val viewModel: HomeViewModel = hiltViewModel()

}