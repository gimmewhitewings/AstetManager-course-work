package com.example.astetmanager.data.entities.enums

import com.example.astetmanager.R

enum class Counterparty {
    WILDBERRIES,
    OZON,
    AVITO,
    WHATSAPP,
    INSTAGRAM,
    VK
}

fun Counterparty.getStringResourceId(): Int {
    return when (this) {
        Counterparty.WILDBERRIES -> R.string.wildberries
        Counterparty.OZON -> R.string.ozon
        Counterparty.AVITO -> R.string.avito
        Counterparty.WHATSAPP -> R.string.whatsapp
        Counterparty.INSTAGRAM -> R.string.instagram
        Counterparty.VK -> R.string.vk
    }
}
