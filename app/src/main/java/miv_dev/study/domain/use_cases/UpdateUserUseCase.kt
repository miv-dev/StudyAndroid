package miv_dev.study.domain.use_cases

import miv_dev.study.domain.UserRepository
import miv_dev.study.domain.entity.User
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) = userRepository.updateUser(user)
}