package com.holme.be_app.config

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAIConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.time.Duration.Companion.seconds

@Configuration
class GPTConfig {
    @Bean
    fun generateConfig(): OpenAIConfig?{
        val token: String? = System.getenv("OPENAI_API_KEY")

        return if(token == null){
            println("ERROR!!!: Cannot read token")
            null
        }else{
            OpenAIConfig(
                token,
                timeout = Timeout(socket = 60.seconds)
            )
        }
    }
}