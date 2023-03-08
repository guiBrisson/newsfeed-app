package me.brisson.newsfeed.data.preview_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.brisson.newsfeed.domain.model.Channel
import me.brisson.newsfeed.domain.model.ChannelRss

class ChannelsPreviewProvider : PreviewParameterProvider<List<Channel>> {
    override val values: Sequence<List<Channel>>
        get() = sequenceOf(
            listOf(
                Channel(
                    id = "1",
                    name = "G1",
                    rss = listOf(
                        ChannelRss(
                            id = "brasil_g1",
                            name = "Brasil",
                            ref = "http://g1.globo.com/dynamo/brasil/rss2.xml"
                        ),
                        ChannelRss(
                            id = "carros_g1",
                            name = "Carros (Autoesporte.com)",
                            ref = "http://g1.globo.com/dynamo/carros/rss2.xml"
                        ),
                        ChannelRss(
                            id = "economia_g1",
                            name = "Economia",
                            ref = "http://g1.globo.com/dynamo/economia/rss2.xml"
                        ),
                    )
                ),
                Channel(
                    id = "2",
                    name = "GAZETA DO POVO",
                    rss = listOf(
                        ChannelRss(
                            id = "republica_gzt",
                            name = "República",
                            ref = "https://www.gazetadopovo.com.br/feed/rss/republica.xml"
                        ),
                        ChannelRss(
                            id = "economia_gzt",
                            name = "Economia",
                            ref = "https://www.gazetadopovo.com.br/feed/rss/economia.xml"
                        ),
                        ChannelRss(
                            id = "mundo_gzt",
                            name = "Mundo",
                            ref = "https://www.gazetadopovo.com.br/feed/rss/mundo.xml"
                        ),
                    )
                ),

                )
        )
}