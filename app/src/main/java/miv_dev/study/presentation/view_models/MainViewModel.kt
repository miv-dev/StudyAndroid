package miv_dev.study.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import miv_dev.study.domain.use_cases.GetUserUseCase
import javax.inject.Inject


class MainUIState {
    var isLogged by mutableStateOf(false)
}

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MainUIState())

    init {
        val user = getUserUseCase()
        uiState.isLogged = user != null
    }
}