package com.resende.eventsmicroservice.services;

import com.resende.eventsmicroservice.domain.Subscription;
import com.resende.eventsmicroservice.dtos.EmailRequestDTO;
import com.resende.eventsmicroservice.dtos.EventRequestDTO;
import com.resende.eventsmicroservice.exceptions.EventFullException;
import com.resende.eventsmicroservice.exceptions.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resende.eventsmicroservice.domain.Event;

import com.resende.eventsmicroservice.repositories.EventRepository;
import com.resende.eventsmicroservice.repositories.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @Autowired
  private EmailServiceClient emailServiceClient;


  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public List<Event> getUpComingEvents(){
    return eventRepository.findUpcomingEvents(LocalDateTime.now());
  }

  public Event createEvent(EventRequestDTO eventRequestDTO) {
    Event newEvent = new Event(eventRequestDTO);
    return eventRepository.save(newEvent);
  }

  private Boolean isEventFull(Event event){
    return event.getRegisteredParticipants() >= event.getMaxParticipants();
  }

  public void registerParticipants(String eventId, String participantsEmail) {
    Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

    if (isEventFull(event)) {
      throw new EventFullException();
    }

    Subscription subscription = new Subscription(event, participantsEmail);
    subscriptionRepository.save(subscription);

    event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);
    System.out.println(event.getRegisteredParticipants());

    EmailRequestDTO emailRequestDTO = new EmailRequestDTO(participantsEmail,"Registration confirmed", "You have been successfully registered for the event!");

    emailServiceClient.sendEmail(emailRequestDTO);


  }

}
