package miv_dev.study.domain.use_cases

import miv_dev.study.domain.Repository
import javax.inject.Inject

class InternetConnectionListener @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = repository.networkConnection
}