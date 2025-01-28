package com.example.teaworkshop.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ItemListScreen(effectHandler: EffectHandler) {
    var state: State by remember { mutableStateOf(State.Loading) }
    val coroutineScope = rememberCoroutineScope()

    fun dispatch(msg: Message) {
        val (newState, effect) = reducer(state, msg)
        state = newState
        coroutineScope.launch {
            effect?.let {
                effectHandler.handleEffect(it).collect {
                    dispatch(it)
                }
            }
        }
    }

    LaunchedEffect(effectHandler) {
        dispatch(Message.LoadData)
    }
    ScreenContent(state)
}

@Composable
private fun ScreenContent(state: State) {
    when (state) {
        is State.Loading -> Text("Loading...")
        is State.Content -> Content(state)
    }
}

@Composable
private fun Content(state: State.Content) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.items) { item ->
            ItemRow(
                item = item,
                onLikeClicked = {})
        }
    }
}