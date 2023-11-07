package com.holme.be_app.api.signin.service

import com.holme.be_app.api.signin.entity.SignInResponse
import com.holme.be_app.dto.SafeServiceUserDto
import com.holme.be_app.repository.ServiceUserRepository
import com.holme.be_app.utils.HashFunction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SignInService(
    val serviceUserRepository: ServiceUserRepository,
    @Autowired val hashFunction: HashFunction
) {

    fun signInUser(ident: String, password: String): SignInResponse {
        try{
            val hashedPassword: String = hashFunction.getSHAValue(password)

            val resp = serviceUserRepository.findByIdentAndPassword(ident, hashedPassword) ?: throw Error("No Such User")

            return SignInResponse(
                true,
                null,
                SafeServiceUserDto(
                    resp.id!!,
                    resp.name,
                    resp.ident
                )
            )

        }catch (e: Error){
            println("Sign In Failed")
            return SignInResponse(false, e.message, null)
        }
    }
}