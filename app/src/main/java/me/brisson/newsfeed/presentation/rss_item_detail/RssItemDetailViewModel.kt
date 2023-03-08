package me.brisson.newsfeed.presentation.rss_item_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.newsfeed.AppDestinationArgs
import me.brisson.newsfeed.domain.model.Item
import javax.inject.Inject

@HiltViewModel
class RssItemDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val rssItem: Item? = savedStateHandle[AppDestinationArgs.RSS_ITEM_ARGS]
    private val channelName: String? = savedStateHandle[AppDestinationArgs.CHANNEL_NAME_ARG]

    private val _uiState = MutableStateFlow(RssItemDetailUiState())
    val uiState: StateFlow<RssItemDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(channelName = channelName ?: "", item = rssItem) }
        }
    }
}