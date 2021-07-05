package com.henry.order.service

import com.henry.common.event.OrderCreatedEvent
import com.henry.common.event.PaymentEvent
import com.henry.order.domain.OrderEntity
import com.henry.order.dto.OrderRequest
import com.henry.order.enum.OrderStatus
import com.henry.order.repository.OrderRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.gateway.EventGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val eventGateway: EventGateway,
    private val commandGateway: CommandGateway,
    private val orderRepository: OrderRepository
) {
    fun createOrder(orderRequest: OrderRequest): Int {
        val newOrder = orderRepository.save(
            OrderEntity(
                name = orderRequest.name,
                price = orderRequest.price,
                status = OrderStatus.PENDING
            )
        )

        // send는 비동기, sendAndWait는 동기
        eventGateway.publish(OrderCreatedEvent(newOrder.id, orderRequest.price))

        return newOrder.id
    }
}
