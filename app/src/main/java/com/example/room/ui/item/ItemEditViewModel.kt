package com.example.room.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.room.data.ItemsRepository

/*ItemEditViewModel memiliki ViewModel untuk mengedit atau menambahkan item baru ke Room Database.*/
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    //itemUiState berisi detail item yang sedang diubah.
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    suspend fun saveItem() {
        if (validateInput()) {
            if (itemId > 0) {
                itemsRepository.updateItem(itemUiState.itemDetails.toItem())
            } else {
                itemsRepository.insertItem(itemUiState.itemDetails.toItem())
            }
        }
    }
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

}
