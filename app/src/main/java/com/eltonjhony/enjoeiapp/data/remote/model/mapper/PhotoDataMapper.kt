package com.eltonjhony.enjoeiapp.data.remote.model.mapper

import com.eltonjhony.enjoeiapp.data.DataMapper
import com.eltonjhony.enjoeiapp.data.remote.model.PhotoResponse
import com.eltonjhony.enjoeiapp.domain.Photo

object PhotoDataMapper : DataMapper<PhotoResponse, Photo>() {

    override fun transform(entity: PhotoResponse): Photo {
        return Photo(entity.publicId, entity.crop, entity.gravity)
    }

    override fun transform(entities: List<PhotoResponse>): List<Photo> {
        return entities.map { transform(it) }
    }
}