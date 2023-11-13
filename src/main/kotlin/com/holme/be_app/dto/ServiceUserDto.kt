package com.holme.be_app.dto

import com.holme.be_app.entity.ServiceUser

class ServiceUserDto(
    var id: Int?,
    var name: String,
    var ident: String,
    var password: String
)

fun ServiceUserDto.toEntity(): ServiceUser {
    return ServiceUser(
        this.id,
        this.name,
        this.ident,
        this.password
    )
}


