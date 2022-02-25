package miv_dev.study.domain.use_cases

import miv_dev.study.domain.Repository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = repository.logout()
}