package com.holme.be_app.entity

import com.holme.be_app.dto.SafeServiceUserDto
import com.holme.be_app.dto.ServiceUserDto
import jakarta.persistence.*

@Entity
class ServiceUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val ident: String,

    @Column(nullable = false)
    val password: String,

    // One-to-Many relationship with InstanceSetting
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    // FetchType.EAGER: IOT resolve lost persistence error (failed to lazily initialize a collection of role)
    val instanceSettings: MutableList<InstanceSetting>? = mutableListOf()
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