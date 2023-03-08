package me.brisson.newsfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import me.brisson.newsfeed.domain.ConnectivityObserver
import me.brisson.newsfeed.presentation.theme.NewsFeedTheme
import me.brisson.newsfeed.util.ConnectionStatus
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsFeedTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Available
                )

                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)) {
                    AnimatedVisibility(visible = status != ConnectivityObserver.Status.Available) {
                        ConnectionStatus(status = status)
                    }
                    AppNavGraph()
                }
            }
        }
    }
}

