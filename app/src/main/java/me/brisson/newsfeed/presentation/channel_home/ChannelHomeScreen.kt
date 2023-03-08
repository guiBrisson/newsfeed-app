package me.brisson.newsfeed.presentation.channel_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import me.brisson.newsfeed.R
import me.brisson.newsfeed.domain.model.Item
import me.brisson.newsfeed.domain.model.sortItemsByDate
import me.brisson.newsfeed.util.Header
import retrofit2.HttpException

@ExperimentalMaterialApi
@Composable
fun ChannelHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ChannelHomeViewModel = hiltViewModel(),
    onItem: (rss: Item, channelName: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val pullRefreshState = rememberPullRefreshState(uiState.refreshing, {
        uiState.currentCategory?.let { viewModel.fetchRssByCategory(it) }
        uiState.currentChannelRss?.let { viewModel.fetchRss(it) }
    })

    val anyException = (uiState.errorChannels ?: uiState.errorChannel ?: uiState.errorRss
    ?: uiState.categoriesError)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = (uiState.errorChannels == null),
        drawerContent = {
            DrawerContent(
                modifier = Modifier,
                channels = uiState.channels,
                categories = uiState.categories,
                onClose = { coroutineScope.launch { scaffoldState.drawerState.close() } },
                onChannel = { channel ->
                    viewModel.fetchChannelById(channel.id)
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                },
                onCategory = { category ->
                    viewModel.fetchRssByCategory(category)
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            ) {
                uiState.channels?.let {
                    item {
                        Header(
                            title = uiState.channel?.name ?: uiState.currentCategory,
                            icon = Icons.Default.Menu,
                            iconContentDescription = stringResource(id = R.string.arrow_back_icon_description),
                            onIcon = { coroutineScope.launch { scaffoldState.drawerState.open() } }
                        )
                    }
                }

                uiState.channel?.let { channel ->
                    item {
                        LazyRow(
                            modifier = Modifier
                                .padding(top = 0.dp, bottom = 10.dp)
                                .height(40.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            items(channel.rss) { channelRss ->
                                ChannelRssItem(
                                    modifier = Modifier,
                                    channelRss = channelRss,
                                    currentChannelRss = uiState.currentChannelRss,
                                    clickEnabled = !uiState.loadingChannel,
                                    onClick = { viewModel.fetchRss(channelRss) }
                                )
                            }
                        }
                    }
                }

                item {
                    if (uiState.loadingRss) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }

                uiState.rss?.let { rss ->
                    val items: ArrayList<Item> = arrayListOf()
                    rss.map { it.items }
                        .forEach {
                            it.forEach { item -> items.add(item) }
                        }

                    itemsIndexed(items.toList().sortItemsByDate()) { index, item ->
                        if (index == 0) {
                            HeroNewsItem(
                                modifier = Modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    bottom = 32.dp,
                                    top = 0.dp
                                ),
                                item = item,
                                onClick = {
                                    onItem(
                                        item,
                                        uiState.channel?.name ?: uiState.currentCategory ?: ""
                                    )
                                }
                            )
                        } else {
                            NewsItem(
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 32.dp),
                                item = item,
                                onClick = {
                                    onItem(
                                        item,
                                        uiState.channel?.name ?: uiState.currentCategory ?: ""
                                    )
                                }
                            )
                        }

                        if (index != items.lastIndex) {
                            DottedDivider(
                                modifier = Modifier
                                    .padding(vertical = 0.dp, horizontal = 20.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }

            if (uiState.loadingChannels) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.onBackground
                )
            }

            (anyException)?.let {
                when (it.exception) {
                    is HttpException -> {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = stringResource(id = R.string.server_error_title),
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.onBackground
                            )
                            Text(
                                modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                                text = stringResource(id = R.string.server_error_description),
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onBackground,
                                textAlign = TextAlign.Center,
                            )
                            TextButton(onClick = {
                                viewModel.fetchAllChannels()
                                viewModel.fetchAllCategories()
                            }) {
                                Text(
                                    text = stringResource(id = R.string.try_again),
                                    style = MaterialTheme.typography.button,
                                    color = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }
                    else -> {}
                }
            }

//            PullRefreshIndicator(
//                modifier = Modifier.align(Alignment.TopCenter),
//                refreshing = uiState.refreshing,
//                state = pullRefreshState,
//            )

        }
    }
}

