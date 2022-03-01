package miv_dev.study.domain.use_cases

import miv_dev.study.domain.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.logout()
}