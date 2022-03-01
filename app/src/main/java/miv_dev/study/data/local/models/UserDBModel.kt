package miv_dev.study.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import miv_dev.study.domain.entity.UserType

@Entity(tableName = "user")
data class UserDBModel(
    @PrimaryKey
    val uid: String,
    val email: String,
    val name: String,
    val photoUrl: String?,
    val emailVerified: Boolean = false,
    val userType: UserType = UserType.Student
)