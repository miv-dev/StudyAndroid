package miv_dev.study.domain.entity

data class User(
    val uid: String,
    val email: String,
    val name: String,
    val photoUrl: String?,
    val emailVerified: Boolean = false,
    val userType: UserType = UserType.Student
)

enum class UserType {
    Student, Admin, Adviser, Teacher
}