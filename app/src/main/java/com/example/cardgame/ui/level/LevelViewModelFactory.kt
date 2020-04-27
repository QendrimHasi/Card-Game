package com.example.cardgame.ui.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.data.repositories.LevelRepository

class LevelViewModelFactory (
    private val repository: LevelRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LevelViewMode(repository) as T
    }
}