package com.example.teaworkshop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teaworkshop.data.Item
import com.example.teaworkshop.domain.GetItemsUseCase
import com.example.teaworkshop.domain.UpdateItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase, private val updateItemUseCase: UpdateItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Item>>(emptyList())
    val uiState: StateFlow<List<Item>> = _uiState

    init {
        viewModelScope.launch {
            getItemsUseCase().collect { items ->
                _uiState.value = items
            }
        }
    }

    fun toggleLike(item: Item) {
        viewModelScope.launch {
            updateItemUseCase(item.copy(isLiked = !item.isLiked)).collect()
        }
    }
}