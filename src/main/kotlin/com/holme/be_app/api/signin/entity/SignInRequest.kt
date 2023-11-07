package com.holme.be_app.api.signin.entity

import com.holme.be_app.api.entity.request.SingleRequest

class SignInRequest (
    val enteredID: String,
    val enteredPW: String
)

class SingleSignInRequest (
    payload: SignInRequest
): SingleRequest<SignInRequest>(payload)