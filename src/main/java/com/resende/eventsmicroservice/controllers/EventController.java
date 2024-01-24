package com.resende.eventsmicroservice.controllers;

import com.resende.eventsmicroservice.domain.Event;
import com.resende.eventsmicroservice.dtos.EventRequestDTO;
import com.resende.eventsmicroservice.dtos.SubscriptionRequestDTO;
import com.resende.eventsmicroservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/upcoming")
    public List<Event> getUpComingEvents() {
        return eventService.getUpComingEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequestDTO event) {
        return eventService.createEvent(event);
    }

    @PostMapping("/{eventId}/register")
    public void registerParticipant(@PathVariable String eventId, @RequestBody SubscriptionRequestDTO subscriptionRequest) {
        eventService.registerParticipants(eventId, subscriptionRequest.participantEmail());
    }


}
