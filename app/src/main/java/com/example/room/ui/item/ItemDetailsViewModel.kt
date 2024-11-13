package com.example.room.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*ItemDetailsViewModel untuk mengelola data detail item tertentu yang tersedia di
Room Database melalui ItemsRepository.*/
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])
    //Data disimpan dalam _itemIoState yang dikirim UI sebagai itemUiState
    private val _itemUiState = MutableStateFlow<ItemUiState?>(null)
    val itemUiState: StateFlow<ItemUiState?> = _itemUiState.asStateFlow()

    init {
        viewModelScope.launch {
            itemsRepository.getItemStream(itemId).collect { item ->
                _itemUiState.value = item?.toItemUiState()
            }
        }
    }
//    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])
//
//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//    }
}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)