package com.example.kafka.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.lang.Nullable
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback

@Component
class KafkaService(
    val kafkaTemplate: KafkaTemplate<String, String>
) {

    @Value(value = "\${kafka.topic}")
    private lateinit var topic: String

    @Async
    fun sendMessage(message: String) {
//        val future = kafkaTemplate.send(topic, Payload(message, "testing payload"))
        val future = kafkaTemplate.send(topic, message)

        future.addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
            override fun onSuccess(@Nullable result: SendResult<String?, String?>?) {
                println("Sent message=[$message] with offset=[${result?.recordMetadata?.offset().toString()}]")
            }

            override fun onFailure(ex: Throwable) {
                println("Unable to send message=[$message] due to : ${ex.message}")
            }
        })
    }

}

class Payload(
    val msg: String,
    val name: String
)