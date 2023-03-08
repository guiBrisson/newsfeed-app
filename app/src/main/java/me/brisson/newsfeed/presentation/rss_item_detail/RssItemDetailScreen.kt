package me.brisson.newsfeed.presentation.rss_item_detail

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.brisson.newsfeed.presentation.theme.merriWeather
import me.brisson.newsfeed.util.Header

@Composable
fun RssItemDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: RssItemDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onReadMore: (url: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(bottom = 32.dp),
    ) {
        item {
            Header(title = uiState.channelName, onIcon = onBack)
        }

        uiState.item?.let { item ->
            item {
                val image = if (item.enclosures.isNotEmpty()) {
                    item.enclosures.first().url
                } else {
                    item.media?.thumbnail?.url
                }

                if (!image.isNullOrEmpty()) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(bottom = 18.dp)
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
            }

            item {
                (item.created ?: item.published)?.let {
                    val now = System.currentTimeMillis()
                    val dateFormatted =
                        DateUtils.getRelativeTimeSpanString(it, now, DateUtils.MINUTE_IN_MILLIS)

                    Text(
                        modifier = Modifier.padding(start = 24.dp, top = 0.dp),
                        text = dateFormatted.toString().uppercase(),
                        style = MaterialTheme.typography.overline,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp),
                    text = item.title ?: "",
                    color = MaterialTheme.colors.onBackground,
                    style = TextStyle(
                        fontFamily = merriWeather,
                        fontWeight = FontWeight(900),
                        fontStyle = FontStyle.Italic,
                        fontSize = 26.sp,
                        lineHeight = 34.8.sp
                    )
                )
            }

            item {
                if (!item.description.isNullOrEmpty()) {
                    val description = item.description
                        .replace("\n", "\n\n")
                        .replace("<[^>]*>".toRegex(), "")

                    val color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)

                    Text(
                        modifier = Modifier.padding(top = 32.dp, start = 20.dp, end = 20.dp),
                        text = description,
                        style = MaterialTheme.typography.body1,
                        color = color,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            (item.link ?: item.id)?.let { link ->
                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 32.dp),
                        onClick = { onReadMore(link) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.onBackground,
                            contentColor = MaterialTheme.colors.background
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            hoveredElevation = 0.dp,
                            focusedElevation = 0.dp
                        ),
                        shape = RectangleShape
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(end = 4.dp),
                                text = "Leia toda a matéria aqui",
                                style = MaterialTheme.typography.button,
                                textAlign = TextAlign.End
                            )

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = ""
                            )
                        }
                    }

                }
            }
        }
    }
}
