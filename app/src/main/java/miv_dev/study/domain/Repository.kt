package miv_dev.study.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    val networkConnection: Flow<Boolean>

}