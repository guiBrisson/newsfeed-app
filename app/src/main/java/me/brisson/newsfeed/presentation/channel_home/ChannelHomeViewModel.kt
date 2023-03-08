package me.brisson.newsfeed.presentation.channel_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.newsfeed.domain.model.ChannelRss
import me.brisson.newsfeed.domain.model.Result
import me.brisson.newsfeed.domain.repository.CategoryRepository
import me.brisson.newsfeed.domain.repository.ChannelRepository
import me.brisson.newsfeed.domain.repository.RssRepository
import javax.inject.Inject

@HiltViewModel
class ChannelHomeViewModel @Inject constructor(
    private val rssRepository: RssRepository,
    private val channelRepository: ChannelRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChannelHomeUiState())
    val uiState: StateFlow<ChannelHomeUiState> = _uiState.asStateFlow()

    init {
        fetchAllChannels()
        fetchAllCategories()
    }

    fun fetchAllChannels() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loadingChannels = true,
                    channel = null,
                    errorChannels = null
                )
            }
            when (val result = channelRepository.getAllChannels()) {
                is Result.Success -> {
                    val channels = result.value
                    _uiState.update { it.copy(loadingChannels = false, channels = channels) }
                    fetchChannelById(channels.first().id)
                }
                is Result.Failure -> {
                    _uiState.update { it.copy(loadingChannels = false, errorChannels = result) }
                }
            }
        }
    }

    fun fetchChannelById(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingChannel = true) }
            when (val result = channelRepository.getChannelById(id)) {
                is Result.Success -> {
                    val channel = result.value
                    _uiState.update {
                        it.copy(
                            loadingChannel = false,
                            channel = channel,
                            currentCategory = null,
                            errorChannel = null
                        )
                    }
                    fetchRss(channelRss = channel.rss.first())
                }
                is Result.Failure -> {
                    _uiState.update {
                        it.copy(
                            loadingChannel = false,
                            channel = null,
                            errorChannel = result
                        )
                    }
                }
            }
        }
    }

    fun fetchRss(channelRss: ChannelRss) {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingRss = true) }
            when (val result = rssRepository.getRssById(channelRss.id)) {
                is Result.Success -> {
                    val rss = result.value
                    _uiState.update {
                        it.copy(
                            loadingRss = false,
                            rss = listOf(rss),
                            currentChannelRss = channelRss,
                            errorRss = null
                        )
                    }
                }
                is Result.Failure -> {
                    _uiState.update {
                        it.copy(
                            loadingRss = false,
                            errorRss = result,
                            currentChannelRss = null,
                            rss = null
                        )
                    }
                }
            }
        }
    }

    fun fetchAllCategories() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loadingCategories = true)
            }
            when (val result = categoryRepository.getAllCategories()) {
                is Result.Success -> {
                    val categories = result.value
                    _uiState.update {
                        it.copy(
                            loadingCategories = false,
                            categories = categories,
                            categoriesError = null
                        )
                    }
                }
                is Result.Failure -> {
                    _uiState.update {
                        it.copy(
                            loadingCategories = false,
                            categories = null,
                            categoriesError = result
                        )
                    }
                }
            }
        }
    }

    fun fetchRssByCategory(category: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingRss = true) }
            when (val result = categoryRepository.getRssByCategory(category)) {
                is Result.Success -> {
                    val rss = result.value
                    _uiState.update {
                        it.copy(
                            loadingRss = false,
                            currentCategory = category,
                            rss = rss,
                            currentChannelRss = null,
                            channel = null,
                            errorRss = null
                        )
                    }
                }
                is Result.Failure -> {
                    _uiState.update {
                        it.copy(
                            loadingRss = false,
                            errorRss = result,
                            rss = null,
                            currentCategory = null,
                            currentChannelRss = null,
                            channel = null,
                        )
                    }
                }
            }
        }
    }

}
