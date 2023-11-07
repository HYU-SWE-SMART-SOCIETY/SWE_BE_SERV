package com.holme.be_app.entity

import jakarta.persistence.*

@Entity
class ServiceUser (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val ident: String,

    @Column(nullable = false)
    val password: String,
)