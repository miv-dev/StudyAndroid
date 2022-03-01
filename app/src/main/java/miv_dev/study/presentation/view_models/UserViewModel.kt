package miv_dev.study.presentation.view_models

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import miv_dev.study.domain.entity.User
import miv_dev.study.domain.use_cases.*
import javax.inject.Inject

enum class LoadingState {
    Loading, Loaded, Error, Empty
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {

    var userState by mutableStateOf(UserState())
        private set
    var formState by mutableStateOf(FormState())
        private set

    init {
        loadUser()
    }


    fun clearFormState() {
        formState = FormState()
    }

    private fun loadUser() {
        viewModelScope.launch {
            getUserUseCase().collect { user: User? ->
                println(user?.name)
                if (user == null) {
                    userState.user = null
                    userState.isLogged = false
                } else {
                    userState.user = user
                    userState.isLogged = true
                }
            }

        }
    }

    fun login() {
        if (validateForm()) {
            formState.loadingState = LoadingState.Loading
            viewModelScope.launch {
                val resp = loginUseCase(formState.email, formState.password)
                resp.fold(
                    {
                        formState.loadingState = LoadingState.Loaded
                    },
                    { failure ->
                        formState.loadingState = LoadingState.Error
                        formState.passwordError = failure.localizedMessage ?: "Error"
                    }
                )
            }
        } else {
            formState.loadingState = LoadingState.Error
        }

    }
    fun update(user: User){
        userState.user = user
        viewModelScope.launch{
            updateUserUseCase(user)
        }
    }

    fun register() {
        if (validateForm()) {
            formState.loadingState = LoadingState.Loading
            viewModelScope.launch {
                val resp = registerUseCase (formState.email, formState.password)
                resp.fold(
                    {
                        formState.loadingState = LoadingState.Loaded
                    },
                    { failure ->
                        formState.loadingState = LoadingState.Error
                        formState.passwordError = failure.localizedMessage ?: "Error"
                    }
                )
            }
        } else {
            formState.loadingState = LoadingState.Error
        }

    }
    private fun validateForm(): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        var result = true
        if (formState.email.isNotEmpty()) {
            if (!pattern.matcher(formState.email).matches()) {
                formState.emailError = "Non valid email"
                result = false
            }
        } else {
            formState.emailError = "Enter email"
            result = false
        }

        if (formState.password.isEmpty()) {
            formState.passwordError = "Enter password"
            result = false
        }
        return result
    }

    fun logout() {
        userState.isLogged = false
        userState.user = null
        logoutUseCase()
    }

    companion object {

        class FormState {
            var email: String by mutableStateOf("")
            var password: String by mutableStateOf("")
            var emailError: String by mutableStateOf("")
            var passwordError: String by mutableStateOf("")
            var loadingState: LoadingState by mutableStateOf(LoadingState.Empty)
        }

        class UserState {
            var user: User? by mutableStateOf(null)
            var isLogged: Boolean by mutableStateOf(false)
        }
    }
}