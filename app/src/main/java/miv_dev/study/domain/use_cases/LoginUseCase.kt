package miv_dev.study.domain.use_cases

import miv_dev.study.domain.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String) = userRepository.login(email, password)
}
