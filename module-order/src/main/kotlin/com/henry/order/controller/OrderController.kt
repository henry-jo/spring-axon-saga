package com.henry.order.controller

import com.henry.order.dto.OrderRequest
import com.henry.order.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/order")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest): Int {
        return orderService.createOrder(orderRequest)
    }
}
