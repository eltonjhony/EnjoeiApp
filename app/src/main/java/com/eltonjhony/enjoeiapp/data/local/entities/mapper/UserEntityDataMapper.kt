package com.eltonjhony.enjoeiapp.data.local.entities.mapper

import com.eltonjhony.enjoeiapp.data.DataMapper
import com.eltonjhony.enjoeiapp.data.local.entities.UserEntity
import com.eltonjhony.enjoeiapp.data.remote.model.UserResponse
import com.eltonjhony.enjoeiapp.domain.User

object UserEntityDataMapper : DataMapper<UserResponse, UserEntity>() {

    override fun transform(entity: UserResponse): UserEntity {
        return UserEntity(entity.id, entity.name, PhotoEntityDataMapper.transform(entity.avatar))
    }

    override fun transform(entities: List<UserResponse>): List<UserEntity> {
        return entities.map { transform(it) }
    }

}

object UserDataMapper : DataMapper<UserEntity, User>() {

    override fun transform(entity: UserEntity): User {
        return User(entity.id, entity.name, PhotoDataMapper.transform(entity.avatar))
    }

    override fun transform(entities: List<UserEntity>): List<User> {
        return entities.map { transform(it) }
    }

}