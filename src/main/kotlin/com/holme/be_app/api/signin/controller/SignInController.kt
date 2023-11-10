package com.holme.be_app.api.signin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.signin.entity.SignInRequest
import com.holme.be_app.api.signin.entity.SignInResponse
import com.holme.be_app.api.signin.entity.SingleSignInRequest
import com.holme.be_app.api.signin.service.SignInService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/signin")
class SignInController(
    @Autowired val signInService: SignInService,
    @Autowired val responseService: SingleResponseService<SignInResponse>
) {

    @PostMapping("/")
    fun handleSignIn(@RequestBody singleSignInRequest: SingleSignInRequest): SingleResponse<SignInResponse> {
        val requestPayload: SignInRequest = singleSignInRequest.payload
        return try{
            val enteredID: String = requestPayload.enteredID
            val enteredPW: String = requestPayload.enteredPW

            val resp = signInService.signInUser(enteredID,enteredPW)

            println(resp.ok)

            if(!resp.ok) throw Error(resp.message)

            responseService.isSuccessful("Login Success!", resp.user)
        }catch (e: Error){
            val message: String = if(e.message is String) e.message!! else e.toString()
            responseService.isFailure(-1, message, null)
        }
    }
}