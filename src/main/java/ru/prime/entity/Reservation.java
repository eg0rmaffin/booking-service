package ru.prime.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "realestate")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Client name cannot be null")
    @Size(min = 1, max = 100, message = "Client name must be between 1 and 100 characters")
    private String clientName;

    @NotNull(message = "Reservation time cannot be null")
    private LocalDateTime reservationTime;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    @NotNull(message = "Lot ID cannot be null")
    private Lot lot;

    @NotNull(message = "Contact phone cannot be null")
    @Pattern(regexp = "\\+?[0-9]+", message = "Invalid phone number")
    private String contactPhone;
}
