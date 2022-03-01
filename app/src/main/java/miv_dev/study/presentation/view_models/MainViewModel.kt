package miv_dev.study.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import miv_dev.study.domain.use_cases.InternetConnectionListener
import javax.inject.Inject




class MainUIState {
    var hasInternetConnection by mutableStateOf(true)
}

@HiltViewModel
class MainViewModel @Inject constructor(
    internetConnectionListener: InternetConnectionListener,
) : ViewModel() {

    var uiState by mutableStateOf(MainUIState())



    init {
        viewModelScope.launch {
            internetConnectionListener().collect {
                uiState.hasInternetConnection = it
            }
        }
    }


    companion object {
        const val TAG = "MainViewModel"
    }
}