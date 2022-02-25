package miv_dev.study.presentation.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import miv_dev.study.domain.use_cases.GetUserUseCase
import miv_dev.study.domain.use_cases.InternetConnectionListener
import miv_dev.study.domain.use_cases.LoginUseCase
import miv_dev.study.domain.use_cases.LogoutUseCase
import javax.inject.Inject

enum class LoadingState {
    Loading, Loaded, Error, Empty
}

class LoginUIState {
    var password by mutableStateOf("")
    var email by mutableStateOf("")
    var loadingState by mutableStateOf(LoadingState.Empty)
    var error by mutableStateOf("")
}




class MainUIState {
    var isLogged by mutableStateOf(false)
    var selectedItem by mutableStateOf(0)
    var hasInternetConnection by mutableStateOf(true)
}

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase,
    internetConnectionListener: InternetConnectionListener,
    private val  loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MainUIState())

    var loginUIState by mutableStateOf(LoginUIState())


    init {
        val user = getUserUseCase()
        uiState.isLogged = user != null
        viewModelScope.launch {
            internetConnectionListener().collect {
                Log.d(TAG, "InternetConnectionState = $it")
                uiState.hasInternetConnection = it
            }
        }
    }

    fun login() {
        loginUIState.loadingState = LoadingState.Loading
        viewModelScope.launch {
            val resp = loginUseCase(loginUIState.email, loginUIState.password)
            resp.fold(
                {
                    loginUIState.loadingState = LoadingState.Loaded
                    uiState.isLogged = true
                },
                { failure ->
                    loginUIState.loadingState = LoadingState.Error
                    loginUIState.error = failure.message ?: "Error"
                }
            )
        }
    }

    fun logout() {
        uiState.isLogged = false
        logoutUseCase()
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}