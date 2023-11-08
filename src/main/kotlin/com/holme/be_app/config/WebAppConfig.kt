package com.holme.be_app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class WebAppConfig {
    @Bean
    fun corsConfigSource(): CorsConfigurationSource {
        //* CORS
        val configuration = CorsConfiguration();

        configuration.allowedOrigins = listOf("*") //TODO: IMPORTANT!! NEED TO ADD WHITELIST IN HERE
        configuration.allowedMethods = listOf("GET","POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("*")
        configuration.maxAge = 3000L
        configuration.allowCredentials = false

        val source = UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);

        return source
    }
}