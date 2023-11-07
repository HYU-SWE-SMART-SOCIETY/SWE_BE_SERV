package com.holme.be_app.api.signup.controller

import com.holme.be_app.api.entity.response.SingleResponse
import com.holme.be_app.api.entity.response.SingleResponseService
import com.holme.be_app.api.signup.entity.SignUpRequest
import com.holme.be_app.api.signup.entity.SignUpResponse
import com.holme.be_app.api.signup.entity.SingleSignUpRequest
import com.holme.be_app.api.signup.service.SignUpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/signup")
class SignUpController(
    @Autowired private val signUpService: SignUpService,
    @Autowired private val responseService: SingleResponseService<SignUpResponse>
) {

    @PostMapping("/")
    fun handleSignUp(@RequestBody singleSignUpRequest: SingleSignUpRequest): SingleResponse<SignUpResponse> {
        val requestPayload:SignUpRequest = singleSignUpRequest.payload
        try{
            val name = requestPayload.name
            val ident = requestPayload.ident
            val rawPassword = requestPayload.rawPassword

            val resp = signUpService.signUpUser(ident,name,rawPassword)

            if(!resp.ok) throw Error(resp.message)

        }catch (e: Error){
            val message: String = if(e.message is String) e.message!! else e.toString()
            return responseService.isFailure(-1, message)
        }

        return responseService.isSuccessful(null)
    }
}