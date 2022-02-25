package miv_dev.study.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class HomeUIState {
}

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    var uiState by mutableStateOf(HomeUIState())

    init {

    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}