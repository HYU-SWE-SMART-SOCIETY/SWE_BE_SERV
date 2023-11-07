package com.holme.be_app.api.signup.service

import org.springframework.stereotype.Service

@Service
class SignUpService {

    fun validateUser(ident: String, name: String): Boolean {
        return try{
            true
        }catch (e: Error){
            false
        }
    }
}