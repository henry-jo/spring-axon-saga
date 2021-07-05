package com.henry.common.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class OrderCompleteCommand(
    @TargetAggregateIdentifier
    val orderId: Int
)
