package com.example.room.data

import kotlinx.coroutines.flow.Flow

/*interface ItemsRepository akan mendefinisikan methods yang
* berinteraksi dengan data pada Item. Mengambil semua item dengan
* getAllItemsStream menggunakan Flow, mengambil item berdasarkan
* id menggunakan getItemStream, menambahkan item pada database
* menggunakan insertItem, menghapus item dari database menggunakan
* deleteItem, memperbarui item di database menggunakan updateItem.*/

interface ItemsRepository {
    fun getAllItemsStream(): Flow<List<Item>>

    fun getItemStream(id: Int): Flow<Item?>

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)
}