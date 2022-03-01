package miv_dev.study.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import miv_dev.study.data.local.models.UserDBModel

@Dao
interface UserDao {
    @Query("select * from user where uid = :uid")
    fun getUser(uid:String): UserDBModel

    @Insert
    fun insertUser(userDBModel: UserDBModel)

    @Update
    fun updateUser(userDBModel: UserDBModel)
}
