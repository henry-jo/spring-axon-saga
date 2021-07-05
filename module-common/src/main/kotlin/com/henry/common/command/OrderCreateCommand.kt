package com.henry.common.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class OrderCreateCommand(
    @TargetAggregateIdentifier // Command를 받을 Aggregate 식별자 지정
    val orderId: Int,
    val price: Int
)
