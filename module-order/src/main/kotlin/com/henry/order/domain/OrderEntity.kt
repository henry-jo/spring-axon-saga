package com.henry.order.domain

import com.henry.order.enum.OrderStatus
import javax.persistence.*

@Entity
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    var name: String,

    var price: Int,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus
)
