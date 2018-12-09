package com.study.multi.docker.aws.controller

import com.study.multi.docker.aws.service.EventService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/values")
class EventController(private val eventService: EventService) {

    @GetMapping("/all")
    fun getAllValues() =
        eventService.getValuesStoredInPostgres()

    @GetMapping("/current")
    fun getCurrentValue(): Set<String> =
        eventService.getValuesStoredInRedis()

    @PostMapping
    fun storeValue(@RequestBody message: String) {
        when (message.toInt() > 40) {
            true -> ResponseEntity.status(422).body("Index too high")
            else -> eventService.publishEvent(message)
        }
    }
}