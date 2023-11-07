package com.holme.be_app.api.signup.entity

import com.holme.be_app.api.entity.request.SingleRequest

class SignUpRequest (
    val name: String,
    val ident: String,
    val rawPassword: String
)

class SingleSignUpRequest(
    payload: SignUpRequest
): SingleRequest<SignUpRequest>(payload)