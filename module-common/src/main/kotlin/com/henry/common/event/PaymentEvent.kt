package com.henry.common.event

import java.io.Serializable

data class PaymentEvent(
    val paymentId: Int,
    val orderId: Int
): Serializable
