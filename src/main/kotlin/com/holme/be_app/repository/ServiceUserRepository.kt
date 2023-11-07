package com.holme.be_app.repository

import com.holme.be_app.entity.ServiceUser
import org.springframework.data.repository.CrudRepository

interface ServiceUserRepository: CrudRepository<ServiceUser, Int> {
    fun findByNameOrIdent(name: String, ident: String):ServiceUser?
    fun findByIdentAndPassword(ident: String, password: String): ServiceUser?
}