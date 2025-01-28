package com.example.teaworkshop.ui

import com.example.teaworkshop.data.Item
import com.example.teaworkshop.domain.GetItemsUseCase
import com.example.teaworkshop.domain.UpdateItemUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

sealed class State {
    data object Loading : State()
    data class Content(val items: List<Item>) : State()
}

sealed class Effect {
    data object LoadData : Effect()
    data class ToggleLike(val item: Item, val newLikeValue: Boolean) : Effect()
}

sealed class Message {
    data object LoadData : Message()
    data class DataLoaded(val result: List<Item>) : Message()
    data class ToggleLike(val item: Item, val newLikeValue: Boolean) : Message()
}

fun reducer(state: State, msg: Message): Pair<State, Effect?> {
    return when (msg) {
        is Message.LoadData -> state to Effect.LoadData
        is Message.ToggleLike -> state to Effect.ToggleLike(msg.item, msg.newLikeValue)
        is Message.DataLoaded -> State.Content(msg.result) to null
    }
}

class EffectHandler @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase, private val updateItemUseCase: UpdateItemUseCase
) {
    fun handleEffect(effect: Effect): Flow<Message> {
        return when (effect) {
            is Effect.LoadData -> {
                getItemsUseCase.invoke().map { Message.DataLoaded(it) }
            }

            is Effect.ToggleLike -> {
                updateItemUseCase.invoke(effect.item.copy(isLiked = effect.newLikeValue))
                    .map { Message.LoadData }
            }
        }
    }
}