package com.example.room.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
}

/*AppDataContainer adalalah implementasi dari InterAppContainer, untuk
mengambil parameter context yang dibutuhkan untuk database*/

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}