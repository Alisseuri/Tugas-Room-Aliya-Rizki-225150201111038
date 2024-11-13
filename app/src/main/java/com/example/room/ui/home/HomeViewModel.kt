package com.example.room.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.data.Item
import com.example.room.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.lang.Error

class HomeViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//    }
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            itemsRepository.getAllItemsStream()
                .catch { e ->
                    _uiState.value = HomeUiState(error = e.message)
                }
                .collect{ items ->
                    _uiState.value = HomeUiState(itemList = items, isLoading = false)
                }
        }
    }
}

data class HomeUiState(
    val itemList: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
