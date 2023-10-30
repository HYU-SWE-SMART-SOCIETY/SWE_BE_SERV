package com.holme.be_app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController{

    @PostMapping("/")
    fun testPost():ResponseEntity<Any>{
        println("RECEIVED!")
        return ResponseEntity.ok().body(true)
    }
}