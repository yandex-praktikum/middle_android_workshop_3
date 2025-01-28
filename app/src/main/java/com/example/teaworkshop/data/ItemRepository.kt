package com.example.teaworkshop.data

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    fun updateItem(item: Item): Flow<Unit>
}