package com.example.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*Menandai data class Item sebagai Entity (Entitas). Entity adalah
perwakilan objek yang ingin disimpan di database, sebagai tempat
penyimpanan data.*/

@Entity(tableName = "items")
data class Item (
    /*Anotasi @PrimaryKey digunakan untuk menentukan primary key
    tabel Item, yaitu id. id akan auto generate secara otomatis
    setiap Entity baru ditambahkan*/

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
) {

    fun formatedPrice(): String {
        return "$%.2f".format(price)
    }
}