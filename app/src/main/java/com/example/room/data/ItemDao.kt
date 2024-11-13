package com.example.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*Data Access Object (DAO) adalah class yang berisi methods
yang digunakan untuk mengakses database seperti insert,
read, update. Dao harus berupa class interface.*/

@Dao
interface ItemDao {
    /*Metode Insert untuk memasukkan data ke dalam tabel Item.*/
    @Insert
    suspend fun insert(item: Item)

    /*Metode Update untuk memperbarui data pada tabel Item.*/
    @Update
    suspend fun update(item: Item)

    /*Metode Delete untuk menghapus data pada tabel Item.*/
    @Delete
    suspend fun delete(item: Item)

    /*Query digunakan untuk menjalankan perintah SQL.*/
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}