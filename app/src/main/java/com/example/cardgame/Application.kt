package com.example.cardgame

import com.example.cardgame.data.db.AppDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import android.app.Application
import com.example.cardgame.data.network.NetworkConnectionInteerception
import com.example.cardgame.data.network.UserService
import com.example.cardgame.data.repositories.LevelRepository
import com.example.cardgame.ui.level.LevelViewModelFactory
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.provider

class Application : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))
        bind() from singleton { NetworkConnectionInteerception(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserService(instance()) }
        bind() from singleton { LevelRepository(instance(),instance()) }
        bind() from provider { LevelViewModelFactory(instance()) }
    }
}