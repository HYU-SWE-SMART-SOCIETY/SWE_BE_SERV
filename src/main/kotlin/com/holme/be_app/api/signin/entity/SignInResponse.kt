package com.holme.be_app.api.signin.entity

import com.holme.be_app.dto.SafeServiceUserDto

class SignInResponse (
    val ok: Boolean,
    val message: String?,
    val user: SafeServiceUserDto?
)