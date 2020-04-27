package com.example.cardgame.ui.level

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.cardgame.data.db.entities.Level
import com.example.cardgame.data.db.entities.Photo
import com.example.cardgame.data.repositories.LevelRepository
import com.example.cardgame.util.ApiException
import com.example.cardgame.util.Coroutines
import com.example.cardgame.util.NoInternetException


class LevelViewMode(
    private val repository: LevelRepository
) : ViewModel() {

    val level = Level(0, 0, false)

    var listener: ErrorListener?= null

    var firstImg: Photo? = null
    var secondImg: Photo? = null

    fun getAllLevels(): LiveData<List<Level>> {
        return repository.getAllLevels()
    }

    fun insertSaveUpdateLevel(level: Level) {
        Coroutines.io {
            repository.insertSaveUpdateLevel(level)
        }
    }

    fun getLevelById(id: Int): LiveData<Level> {
        return repository.getLevelById(id)
    }

    fun getImages(numberOfImage: Int): LiveData<List<Photo>> {
        var mutableList : MutableLiveData<List<Photo>> = MutableLiveData()
        repository.deleteAllPhotoFromSQL()
        try {
            repository.getImages(numberOfImage).observeForever(Observer {
                mutableList.postValue(it)
            })
        }catch (e:ApiException){
            listener?.onFailure(e.message!!)
        }
        return mutableList
    }

    fun imageClicked(photo: Photo, levelid: Int) {
        photo.clickt = true
        if (firstImg == null) {
            firstImg = photo
            repository.insertSinglePhoto(photo)
        } else if (secondImg == null){
            secondImg = photo
            repository.insertSinglePhoto(photo)
            if (firstImg!!.id == secondImg!!.id) {
                firstImg!!.matched = true
                secondImg!!.matched = true
                repository.insertSinglePhoto(firstImg!!)
                repository.insertSinglePhoto(secondImg!!)
                firstImg = null
                secondImg = null

                Coroutines.io{
                    val level = repository.getLevel(levelid)
                    level.score = level.score!! + 10
                    repository.insertSaveUpdateLevel(level)
                }

            } else {

                val mainLooperHandler = Handler(Looper.getMainLooper())
                mainLooperHandler.postDelayed(Runnable {
                    firstImg!!.clickt = false
                    secondImg!!.clickt = false
                    repository.insertSinglePhoto(firstImg!!)
                    repository.insertSinglePhoto(secondImg!!)
                    firstImg = null
                    secondImg = null
                }, 1000)
            }
        }

    }

    fun getListOfPhoto(): LiveData<List<Photo>>{
        return repository.getListOfPhoto()
    }

    fun retartgame(){
        Coroutines.io{
            repository.retartgame()
        }
    }
}