package miv_dev.study.data

import com.google.firebase.auth.FirebaseUser
import miv_dev.study.data.local.models.UserDBModel
import miv_dev.study.domain.entity.User
import miv_dev.study.domain.entity.UserType

class UserMapper {

    fun mapEntityToDbModel(user: User) = UserDBModel(
        uid = user.uid,
        email = user.email,
        name = user.name,
        photoUrl = user.photoUrl,
        emailVerified = user.emailVerified,
        userType = user.userType
    )

    fun mapDbModelToEntity(userDBModel: UserDBModel) = User(
        uid = userDBModel.uid,
        email = userDBModel.email,
        name = userDBModel.name,
        photoUrl = userDBModel.photoUrl,
        emailVerified = userDBModel.emailVerified,
        userType = userDBModel.userType
    )

    fun mapFirestoreModelToEntity(map: Map<String, Any>, user: FirebaseUser) = User(
        uid = user.uid,
        email = user.email!!,
        name = (map["name"] ?: ("User_" + user.uid.substring(0, 5))) as String,
        userType = UserType.valueOf((map["type"] ?: "Student") as String),
        photoUrl = map["photoUrl"] as String?,
        emailVerified = user.isEmailVerified,
    )

    fun toMap(user: User): Map<String, Any?> = mapOf(
        "email" to user.email,
        "name" to user.name,
        "userType" to user.userType.name,
        "photoUrl" to user.photoUrl,
    )

}