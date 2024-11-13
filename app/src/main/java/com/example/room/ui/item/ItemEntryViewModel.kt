package com.example.room.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.room.data.Item
import com.example.room.data.ItemsRepository

/*ItemEntryViewModel mengatur tampilan UI dan bertanggung jawab untuk menyimpan
* item baru ke dalam Room Database.*/
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(itemDetails, isEntryValid = validateInput(itemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            //Berfungsi untuk menyimpan itemDetails dari itemUiState
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

}

data class ItemUiState (
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = ""
)
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
