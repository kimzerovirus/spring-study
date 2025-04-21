package me.kzv.practice.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatClientConfig {

    @Qualifier("openAiChatClient")
    @Bean
    fun openAiChatClient() : ChatClient {
        val openAiChatModel = OpenAiChatModel.builder()
            .build()
        return ChatClient.create(openAiChatModel)
    }
}