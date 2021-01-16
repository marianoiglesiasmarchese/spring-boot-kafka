package com.example.kafka.config

import com.example.kafka.service.KafkaService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MessageSender {

    @Bean
    fun sendDummyMessage(
        kafkaService: KafkaService
    ) = CommandLineRunner {
        kafkaService.sendMessage("Message")
    }

}