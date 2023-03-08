package me.brisson.newsfeed.data.preview_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.brisson.newsfeed.domain.model.ChannelRss

class ChannelRssPreviewProvider : PreviewParameterProvider<List<ChannelRss>> {
    override val values: Sequence<List<ChannelRss>>
        get() = sequenceOf(
            listOf(
                ChannelRss(
                    id = "",
                    name = "Brasil",
                    ref = ""
                ),
                ChannelRss(
                    id = "",
                    name = "Tecnologia",
                    ref = ""
                ),
                ChannelRss(
                    id = "",
                    name = "Ciência",
                    ref = ""
                ),
                ChannelRss(
                    id = "",
                    name = "Saúde",
                    ref = ""
                ),
                ChannelRss(
                    id = "",
                    name = "Economia",
                    ref = ""
                )
            )
        )

}