package com.example.cardgame.data.repositories

import androidx.lifecycle.LiveData
import com.example.cardgame.data.db.AppDatabase
import com.example.cardgame.data.db.entities.Level
import com.example.cardgame.data.db.entities.Photo
import com.example.cardgame.data.network.SafeApiRequest
import com.example.cardgame.data.network.UserService
import com.example.cardgame.data.network.responses.photoResponse
import com.example.cardgame.util.Coroutines
import com.example.cardgame.util.NoInternetException
import java.util.*

class LevelRepository(
    private val db: AppDatabase,
    private val service: UserService
) : SafeApiRequest() {

    fun getAllLevels(): LiveData<List<Level>> {
        return db.getLevelDao().getAllLevels()
    }

    fun insertSaveUpdateLevel(level: Level) {
        Coroutines.io {
            db.getLevelDao().insertSaveUpdateLevel(level)
        }
    }

    fun getLevelById(id: Int): LiveData<Level> {
        return db.getLevelDao().getLevelById(id)
    }

    fun getLevel(id: Int): Level {
        return db.getLevelDao().getLevel(id)
    }
    fun getImages(numberOfImage: Int): LiveData<List<Photo>> {
        Coroutines.io{
            try {
                val photoResponse: photoResponse = apiRequest {
                    service.getimages(
                        "flickr.photos.search",
                        "4bb8ac8aeee86cb97c5d0f698bf40cc8",
                        "dog",
                        "json",
                        1,
                        numberOfImage
                    )
                }
                var photolist = ArrayList<Photo>()
                photolist.addAll(photoResponse.photos.photo)
                photolist.addAll(photoResponse.photos.photo)
                photolist.shuffle()
                photolist.shuffle()
                db.getPhotoDao().insertPhotos(photolist)
            }catch (E:NoInternetException){}
        }
        return db.getPhotoDao().getAllPotos()
    }

    fun deleteAllPhotoFromSQL() {
        Coroutines.io {
            db.getPhotoDao().deleteAllPhotos()
        }
    }

    fun insertSinglePhoto(photo: Photo) {
        Coroutines.io {
            db.getPhotoDao().insertSinglePhoto(photo)
        }
    }

    fun getListOfPhoto(): LiveData<List<Photo>> {
        return db.getPhotoDao().getListOfPhoto(1)
    }

    fun retartgame() {
        db.getPhotoDao().retartgame(false)
    }

}