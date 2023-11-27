package com.holme.be_app.api.report.service

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.holme.be_app.config.GPTConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OpenAIService(
    @Autowired val gptConfig: GPTConfig
){

    fun generatePayload(request: String) {
        val config = gptConfig.generateConfig()

        if(config == null){
            //* ERROR!!
            println("ERROR: Ruined configuration. Please check if open ai key is correct.")
            // TODO: return null
        }else{
            val openAI = OpenAI(config)

            //* Use Chat Completion Request Object
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "Make following information in assistant style using systematic Korean for our IOT application.\n Information: $request"
                    )
                )
            )

            CoroutineScope(Dispatchers.IO).launch {
                val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
                println(completion.choices)
            }

        }
    }
}