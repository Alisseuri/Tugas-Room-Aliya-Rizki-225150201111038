package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*Melakukan annotate @Database untuk menandakan InventoryDatabase sebagai
class database pada Room. entities mendefinisikan entitas yang dikelola,
yaitu Item. InvetoryDatabase adalah class abstract.*/

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    /*Fungsi abstract itemDao akan mengakses methods DAO yang ada
    pada ItemDao (insert, update, delete).*/

    abstract fun itemDao() : ItemDao

    /*Deklarasi companion object digunakan untuk mengakses member dari
    suatu kelas tanpa melalui objek.*/

    companion object {

        /*Variabel Instance bersifat volatile, untuk memastikan nilai
        variabel dibaca di tempat memori tertentu, dan menghindari error.*/

        @Volatile
        private var Instance: InventoryDatabase? = null

        /*Fungsi getDatabase membuat instance jika belum ada atau
        mengambil instance dari InventoryDatabase. Pada blok
        sychronized, membangun database Room dengan databaseBuilder
        jika instance belum ada.*/

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}