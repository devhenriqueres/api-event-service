package com.resende.eventsmicroservice.dtos;

public record EventRequestDTO(int maxParticipants, int registeredParticipants, String date, String title, String description) {
}
