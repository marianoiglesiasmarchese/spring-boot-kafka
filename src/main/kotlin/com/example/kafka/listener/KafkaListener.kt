package com.example.kafka.listener

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaListener {

    @KafkaListener(topics = ["\${kafka.topic:NOT_FOUND}"], groupId = "\${kafka.groupId:NOT_FOUND}")
    fun listenGroup(message: String) {
        println("Received Message in group foo: $message")
    }

    @KafkaListener(topics = ["\${kafka.topic:NOT_FOUND}"])
    fun listen(@Payload message: String, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition: Int) {
        println("Received Message: $message, from partition: $partition")
    }

    @KafkaListener(topics = ["\${kafka.topic:NOT_FOUND}"], containerFactory = "filterKafkaListenerContainerFactory")
    fun listenWithFilter(message: String) {
        println("Received Message in filtered listener: $message")
    }

}