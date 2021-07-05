package com.henry.payment.service

import com.henry.common.event.PaymentEvent
import mu.KLogging
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
class PaymentService {

    companion object: KLogging()

    @EventHandler
    fun onPaymentEvent(paymentEvent: PaymentEvent) {
        logger.info("onPaymentEvent: $paymentEvent")
    }
}
