package com.example.teaworkshop.domain

import com.example.teaworkshop.data.Item
import com.example.teaworkshop.data.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: ItemRepository) {
    operator fun invoke(): Flow<List<Item>> = repository.getItems()
}

class UpdateItemUseCase @Inject constructor(private val repository: ItemRepository) {
    operator fun invoke(item: Item) = repository.updateItem(item)
}