package com.holme.be_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class BeAppApplication

fun main(args: Array<String>) {
	runApplication<BeAppApplication>(*args)
}
