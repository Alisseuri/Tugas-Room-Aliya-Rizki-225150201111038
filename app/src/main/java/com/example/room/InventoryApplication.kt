package com.example.room

import android.app.Application
import com.example.room.data.AppContainer
import com.example.room.data.AppDataContainer

class InventoryApplication : Application() {

    /*Menggunakan instance AppContainer untuk mendapatkan dependencies*/
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}