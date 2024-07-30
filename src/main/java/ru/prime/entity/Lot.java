package ru.prime.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(schema = "realestate")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;
    private double area;
    private int floor;
    private int rooms;
}
