package com.henry.common.event

import java.io.Serializable

data class OrderCreatedEvent(
    val orderId: Int,
    val price: Int
): Serializable
