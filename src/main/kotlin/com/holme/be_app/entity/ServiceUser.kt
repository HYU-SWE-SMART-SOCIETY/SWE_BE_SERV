package com.holme.be_app.entity

import com.holme.be_app.dto.SafeServiceUserDto
import com.holme.be_app.dto.ServiceUserDto
import jakarta.persistence.*

@Entity
class ServiceUser (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val ident: String,

    @Column(nullable = false)
    val password: String,
)

fun ServiceUser.toDto(): ServiceUserDto {
    return ServiceUserDto(
        this.id,
        this.name,
        this.ident,
        this.password
    )
}

fun ServiceUser.toSafeDto(): SafeServiceUserDto {
    return SafeServiceUserDto(
        this.id!!,
        this.name,
        this.ident
    )
}