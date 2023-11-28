package com.holme.be_app.api.report.service

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.holme.be_app.config.GPTConfig
import com.holme.be_app.entity.ReportType
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OpenAIService(
    @Autowired val gptConfig: GPTConfig
){

    suspend fun generatePayload(type:ReportType, request: String): String? {
        val config = gptConfig.generateConfig()

        if(config == null){
            //* ERROR!!
            println("ERROR: Ruined configuration. Please check if open ai key is correct.")
            return null
        }else{
            val openAI = OpenAI(config)

            //* Use Chat Completion Request Object
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    //TODO: Make this code more secure
                    ChatMessage(
                        role = ChatRole.User,
                        content = "Make following information in assistant style using Korean for our IOT application with smooth expression. [Guideline]1. REPLACEMENT should be translated into '대체', AC into '에어컨'. 2.The expression will be in 3 lines. (Successful Status: ${type} (In Full Sentence), Reminder Message, The list of IoTs (This must be translated into Korean!))\n Information: $request"
                    )
                )
            )

            //* Handle Coroutine
            val completionDeferred = CompletableDeferred<String?>()

            CoroutineScope(Dispatchers.IO).launch {
               try{
                   val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
                   val payload = completion.choices[0].message.content
                   completionDeferred.complete(payload)
               }catch (e: Exception) {
                   completionDeferred.completeExceptionally(e)
               }
            }

            return completionDeferred.await()
        }
    }
}