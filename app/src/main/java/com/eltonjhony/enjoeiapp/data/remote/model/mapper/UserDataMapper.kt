package com.eltonjhony.enjoeiapp.data.remote.model.mapper

import com.eltonjhony.enjoeiapp.data.DataMapper
import com.eltonjhony.enjoeiapp.data.remote.model.UserResponse
import com.eltonjhony.enjoeiapp.domain.User

object UserDataMapper : DataMapper<UserResponse, User>() {

    override fun transform(entity: UserResponse): User {
        return User(entity.id, entity.name, PhotoDataMapper.transform(entity.avatar))
    }

    override fun transform(entities: List<UserResponse>): List<User> {
        return entities.map { transform(it) }
    }
}