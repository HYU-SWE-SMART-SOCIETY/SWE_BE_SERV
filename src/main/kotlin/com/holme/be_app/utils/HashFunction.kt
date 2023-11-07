package com.holme.be_app.utils

import org.springframework.stereotype.Component
import java.security.MessageDigest

@Component
class HashFunction {
    fun getSHAValue(raw: String): String {
        val bytes:ByteArray = raw.toByteArray()
        val MD: MessageDigest = MessageDigest.getInstance("SHA-256")
        val digest: ByteArray = MD.digest(bytes)

        return digest.fold("") {
            str: String, it:Byte -> str + "%02x".format(it)
        }
    }
}