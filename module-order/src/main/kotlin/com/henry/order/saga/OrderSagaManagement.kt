package com.henry.order.saga

import com.henry.common.event.OrderCompleteEvent
import com.henry.common.event.OrderCreatedEvent
import com.henry.common.event.PaymentEvent
import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.modelling.saga.EndSaga
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@Saga
class OrderSagaManagement {
    @Autowired
    private lateinit var commandGateway: CommandGateway // DDD를 했다면 Aggregate 커맨트를 통해서 진행가능

    @Autowired
    private lateinit var eventGateway: EventGateway

    companion object : KLogging()

    @StartSaga // saga 인스턴스의 시작점
    @SagaEventHandler(associationProperty = "orderId") // 해당 인스턴스를 유일하게 구별할 수 있는 키를 지정
    fun handle(orderCreatedEvent: OrderCreatedEvent) {
        logger.info("[Start Saga] OrderCreatedEvent: $orderCreatedEvent")
        val paymentId = Random().nextInt(10000)

        //associate Saga
        SagaLifecycle.associateWith("paymentId", paymentId)

        //send commands
//        commandGateway.send<PaymentCommand>(PaymentCommand(paymentId, orderCreatedEvent.orderId))
        // compensation command
//            .exceptionally {  }

        // Aggregate CommandHandler를 안거치고 직접 Event를 전송시키는 방법도 존재
        eventGateway.publish(PaymentEvent(paymentId, orderCreatedEvent.orderId))
    }

    @SagaEventHandler(associationProperty = "paymentId")
    fun handle(paymentEvent: PaymentEvent) {
        logger.info("[Continue Saga] PaymentEvent: $paymentEvent")

        //associate Saga
        SagaLifecycle.associateWith("orderId", paymentEvent.orderId)

        //send commands
//        commandGateway.send<OrderCompleteCommand>(OrderCompleteCommand(paymentEvent.orderId))
        eventGateway.publish(OrderCompleteEvent(paymentEvent.orderId))
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    fun handle(orderCompleteEvent: OrderCompleteEvent) {
        // @EndSaga 대신 아래의 메소드 사용 가능
//        SagaLifecycle.end()
        logger.info("-----------[End Saga] Order Complete-------------")
    }
}
