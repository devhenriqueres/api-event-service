package com.resende.eventsmicroservice.domain;

import com.resende.eventsmicroservice.dtos.EventRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "event") // Dizendo que essa classe representa uma entidade (tabela) e o nome da tabela eh event
@Table(name = "event") //  Especificando mais uma vez o nome da tabela
@Getter
@Setter
@AllArgsConstructor // Geração de construtores
@NoArgsConstructor  // Geração de construtores
@EqualsAndHashCode(of="id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int maxParticipants;
    private int registeredParticipants;
    private String date;
    private String title;
    private String description;


    public Event(EventRequestDTO eventRequestDTO) {
        this.date = eventRequestDTO.date();
        this.maxParticipants = eventRequestDTO.maxParticipants();
        this.registeredParticipants = eventRequestDTO.registeredParticipants();
        this.title = eventRequestDTO.title();
        this.description = eventRequestDTO.description();
    }
}
