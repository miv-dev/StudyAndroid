package miv_dev.study.domain.use_cases

import miv_dev.study.domain.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}
