package com.holme.be_app.api.signup.service

import com.holme.be_app.api.signup.entity.SignUpResponse
import com.holme.be_app.dto.ServiceUserDto
import com.holme.be_app.dto.toEntity
import com.holme.be_app.repository.ServiceUserRepository
import com.holme.be_app.utils.HashFunction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SignUpService(
    @Autowired val serviceUserRepository: ServiceUserRepository,
    @Autowired val hashFunction: HashFunction
) {

    private fun validateUserIdent(ident: String, name: String): Boolean {
        return try{
            // Demo; Need to specify the ident and name in real production.
            val received = serviceUserRepository.findByNameOrIdent(name, ident)
            if(received != null){
                throw Error("User with such id or name Exists")
            }
            true
        }catch (e: Error){
            println("${e.message}, Rejected")
            false
        }
    }

    fun signUpUser(ident: String, name: String, rawPW: String): SignUpResponse {
        try {
            if(!validateUserIdent(ident,name)) throw Error("Rejected - User with such id or name Exists.")
            val hashedPassword: String = hashFunction.getSHAValue(rawPW)

            val res = serviceUserRepository.save(
                ServiceUserDto(null,name, ident, hashedPassword)
                    .toEntity()
            )

            print("User ${res.id} with name ${res.name} created")

            return SignUpResponse(true, "User ${res.id} with name ${res.name} created")
        }catch (e: Error){
            println("Sign Up Failed")
            return SignUpResponse(false, e.message)
        }
    }
}