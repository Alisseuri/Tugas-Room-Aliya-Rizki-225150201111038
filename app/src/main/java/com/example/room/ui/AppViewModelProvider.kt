package com.example.room.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.room.InventoryApplication
import com.example.room.ui.home.HomeViewModel
import com.example.room.ui.item.ItemDetailsViewModel
import com.example.room.ui.item.ItemEditViewModel
import com.example.room.ui.item.ItemEntryViewModel

/*Factory mengimplementasikan viewModelFactory untuk mengatur bagaimana viewModel
akan digunakan. Setiap initializer berfungsi untuk mengatur viewModel tertentu.*/

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            /*ItemEditViewModel digunakan untuk melakukan update data pada database*/
            ItemEditViewModel(
                this.createSavedStateHandle(), inventoryApplication().container.itemsRepository
            )
        }
        initializer {
            /*ItemEditEntryModel digunakan untuk melakukan menambahkan data baru pada database*/
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        initializer {
            /*ItemDetailsViewModel digunakan untuk menampilkan salah satu data pada database*/
            ItemDetailsViewModel(
                this.createSavedStateHandle(), inventoryApplication().container.itemsRepository
            )
        }

        initializer {
            /*HomeViewModel menampilkan list seluruh item (data) pada database*/
            HomeViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
