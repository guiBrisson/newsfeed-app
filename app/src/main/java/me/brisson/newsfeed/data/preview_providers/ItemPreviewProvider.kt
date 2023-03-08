package me.brisson.newsfeed.data.preview_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.brisson.newsfeed.domain.model.Enclosure
import me.brisson.newsfeed.domain.model.Item

class ItemPreviewProvider : PreviewParameterProvider<Item> {
    override val values: Sequence<Item>
        get() = sequenceOf(
            Item(
                title = "Empresa contratada por vinícolas no RS nega irregularidades",
                description = "\"<img src=\\https://media.gazetadopovo.com.br/2023/03/02154817/C%C3%B3pia-de-Apresenta%C3%A7%C3%A3o-Marrom-e-Preta-de-Portf%C3%B3lio-de-Arquitetura-1-960x540.jpg\\\" /><br />A empresa terceirizada pelas vinícolas em Bento Gonçalves (RS) não aceitou a proposta de acordo apresentada pelo MPT-RS. Leia na Gazeta do Povo",
                published = 1677851498000,
                created = 1677851498000,
                enclosures = listOf(
                    Enclosure(
                        url = "https://media.gazetadopovo.com.br/2023/03/02154817/C%C3%B3pia-de-Apresenta%C3%A7%C3%A3o-Marrom-e-Preta-de-Portf%C3%B3lio-de-Arquitetura-1-960x540.jpg"
                    )
                )
            )
        )
}