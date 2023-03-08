package me.brisson.newsfeed.presentation.channel_home

import android.text.format.DateUtils
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import me.brisson.newsfeed.BuildConfig
import me.brisson.newsfeed.R
import me.brisson.newsfeed.data.preview_providers.ChannelRssPreviewProvider
import me.brisson.newsfeed.data.preview_providers.ChannelsPreviewProvider
import me.brisson.newsfeed.data.preview_providers.ItemPreviewProvider
import me.brisson.newsfeed.domain.model.Channel
import me.brisson.newsfeed.domain.model.ChannelRss
import me.brisson.newsfeed.domain.model.Item
import me.brisson.newsfeed.presentation.theme.NewsFeedTheme

@Composable
fun ChannelRssItem(
    modifier: Modifier = Modifier,
    channelRss: ChannelRss,
    currentChannelRss: ChannelRss? = null,
    clickEnabled: Boolean = true,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                enabled = clickEnabled,
                onClick = onClick
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isSelected = currentChannelRss?.name == channelRss.name
        val color = MaterialTheme.colors.onBackground
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "#${channelRss.name}",
            style = MaterialTheme.typography.body2,
            color = color,
            fontWeight = if (isSelected) FontWeight.Bold else null
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        if (isSelected) {
            Canvas(modifier = Modifier.size(4.dp)) {
                drawCircle(color = color)
            }
        }
    }
}

@Composable
fun HeroNewsItem(
    modifier: Modifier = Modifier,
    item: Item,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick
            )
    ) {
        val image = if (item.enclosures.isNotEmpty()) {
            item.enclosures.first().url
        } else {
            item.media?.thumbnail?.url
        }

        if (!image.isNullOrEmpty()) {
            AsyncImage(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.LightGray)
            )
        }

        Text(
            modifier = Modifier.padding(top = 0.dp),
            text = item.title ?: "",
            color = MaterialTheme.colors.onBackground,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1
        )

        (item.created ?: item.published)?.let {
            val now = System.currentTimeMillis()
            val dateFormatted =
                DateUtils.getRelativeTimeSpanString(it, now, DateUtils.MINUTE_IN_MILLIS)
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = dateFormatted.toString(),
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }

    }
}

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    item: Item,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val image = if (item.enclosures.isNotEmpty()) {
            item.enclosures.first().url
        } else {
            item.media?.thumbnail?.url
        }

        val textsWeight = if (!image.isNullOrEmpty()) 0.75f else 1f


        Column(
            modifier = Modifier
                .weight(textsWeight)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier,
                text = item.title ?: "",
                color = MaterialTheme.colors.onBackground,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1
            )
            (item.created ?: item.published)?.let {
                val now = System.currentTimeMillis()
                val dateFormatted =
                    DateUtils.getRelativeTimeSpanString(it, now, DateUtils.MINUTE_IN_MILLIS)
                Text(
                    modifier = Modifier,
                    text = dateFormatted.toString(),
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
        if (!image.isNullOrEmpty()) {
            AsyncImage(
                modifier = Modifier
                    .heightIn(min = 80.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(1 - textsWeight),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .size(256)
                    .precision(Precision.EXACT)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.LightGray)
            )
        }
    }
}

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    channels: List<Channel>?,
    categories: List<String>?,
    onClose: () -> Unit,
    onChannel: (channel: Channel) -> Unit,
    onCategory: (category: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.End
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon_description)
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }

        channels?.let {
            items(it) { channel ->
                DrawerTextItem(
                    modifier = Modifier,
                    text = channel.name,
                    onClick = { onChannel(channel) },
                    paddingValues = PaddingValues(vertical = 15.dp, horizontal = 15.dp)
                )
            }
        }

        categories?.let {
            item {
                DottedDivider(
                    modifier = modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth()
                )
            }
            items(it) { category ->
                DrawerTextItem(
                    modifier = Modifier,
                    text = category,
                    onClick = { onCategory(category) },
                    paddingValues = PaddingValues(vertical = 11.dp, horizontal = 15.dp)
                )
            }
        }
        item {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = stringResource(id = R.string.version_name, BuildConfig.VERSION_NAME),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun DrawerTextItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    paddingValues: PaddingValues
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(paddingValues),
        text = text,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
fun DottedDivider(modifier: Modifier = Modifier) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val color = MaterialTheme.colors.onBackground

    Canvas(modifier = modifier.height(1.dp)) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChannelRssItem(
    @PreviewParameter(ChannelRssPreviewProvider::class)
    channelRssList: List<ChannelRss>
) {
    NewsFeedTheme {
        LazyRow {
            items(channelRssList) { channelRss ->
                ChannelRssItem(
                    modifier = Modifier,
                    channelRss = channelRss,
                    currentChannelRss = channelRssList.first(),
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroNewsItem(@PreviewParameter(ItemPreviewProvider::class) item: Item) {
    NewsFeedTheme {
        HeroNewsItem(item = item, onClick = { })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsItem(@PreviewParameter(ItemPreviewProvider::class) item: Item) {
    NewsFeedTheme {
        NewsItem(item = item, onClick = { })
    }
}

@Preview
@Composable
fun PreviewDrawerContent(
    @PreviewParameter(ChannelsPreviewProvider::class) channels: List<Channel>
) {
    val categories = listOf("main", "brazil", "science", "health", "economy")

    DrawerContent(
        channels = channels,
        categories = categories,
        onClose = { },
        onChannel = { },
        onCategory = { })
}
