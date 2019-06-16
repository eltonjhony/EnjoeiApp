package com.eltonjhony.enjoeiapp.data.local.entities.mapper

import com.eltonjhony.enjoeiapp.data.DataMapper
import com.eltonjhony.enjoeiapp.data.local.entities.PhotoEntity
import com.eltonjhony.enjoeiapp.data.remote.model.PhotoResponse
import com.eltonjhony.enjoeiapp.domain.Photo

object PhotoEntityDataMapper : DataMapper<PhotoResponse, PhotoEntity>() {

    override fun transform(entity: PhotoResponse): PhotoEntity {
        return PhotoEntity(entity.publicId, entity.crop, entity.gravity)
    }

    override fun transform(entities: List<PhotoResponse>): List<PhotoEntity> {
        return entities.map { transform(it) }
    }

}

object PhotoDataMapper : DataMapper<PhotoEntity, Photo>() {

    override fun transform(entity: PhotoEntity): Photo {
        return Photo(entity.publicId, entity.crop, entity.gravity)
    }

    override fun transform(entities: List<PhotoEntity>): List<Photo> {
        return entities.map { transform(it) }
    }

}