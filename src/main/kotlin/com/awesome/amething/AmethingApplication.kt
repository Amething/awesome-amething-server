package com.awesome.amething

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@ConfigurationPropertiesScan
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableWebSecurity
class AmethingApplication

fun main(args: Array<String>) {
	runApplication<AmethingApplication>(*args)

}
