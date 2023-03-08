package me.brisson.newsfeed.util

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.newsfeed.R
import me.brisson.newsfeed.domain.ConnectivityObserver
import me.brisson.newsfeed.presentation.theme.NewsFeedTheme
import me.brisson.newsfeed.presentation.theme.statusBarDark
import me.brisson.newsfeed.presentation.theme.statusBarLight

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: ImageVector = Icons.Default.ArrowBack,
    iconContentDescription: String = stringResource(id = R.string.arrow_back_icon_description),
    onIcon: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onIcon) {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                tint = MaterialTheme.colors.onBackground
            )
        }
        title?.let { name ->
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun ConnectionStatus(
    modifier: Modifier = Modifier,
    status: ConnectivityObserver.Status = ConnectivityObserver.Status.Available
) {
    val backgroundColor: Color = if (isSystemInDarkTheme()) statusBarDark else statusBarLight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        val text: String = when (status) {
            ConnectivityObserver.Status.Unavailable -> {
                stringResource(id = R.string.connection_status_unavailable)}
            ConnectivityObserver.Status.Losing -> {
                stringResource(id = R.string.connection_status_losing)
            }
            ConnectivityObserver.Status.Lost -> {
                stringResource(id = R.string.connection_status_lost)
            }
            ConnectivityObserver.Status.Available -> {
                stringResource(id = R.string.connection_status_available)
            }
        }

        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeader() {
    NewsFeedTheme {
        Header(onIcon = { })
    }
}

@Preview
@Composable
fun PreviewConnectionStatus() {
    NewsFeedTheme {
        ConnectionStatus()
    }
}