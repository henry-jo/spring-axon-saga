package com.henry.common.event

import java.io.Serializable

data class OrderCompleteEvent(
    val orderId: Int
): Serializable
