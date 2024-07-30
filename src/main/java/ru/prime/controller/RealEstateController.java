package ru.prime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.prime.entity.Lot;
import ru.prime.entity.Reservation;
import ru.prime.service.LotService;
import ru.prime.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class RealEstateController {

    private final LotService lotService;
    private final ReservationService reservationService;

    @Autowired
    public RealEstateController(LotService lotService, ReservationService reservationService) {
        this.lotService = lotService;
        this.reservationService = reservationService;
    }

    @GetMapping("/lots")
    public List<Lot> getAllLots() {
        return lotService.getAllLots();
    }

    @GetMapping("/lots/filter")
    public List<Lot> filterLots(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> city,
            @RequestParam Optional<String> address,
            @RequestParam Optional<Double> minArea,
            @RequestParam Optional<Double> maxArea,
            @RequestParam Optional<Integer> minFloor,
            @RequestParam Optional<Integer> maxFloor,
            @RequestParam Optional<Integer> minRooms,
            @RequestParam Optional<Integer> maxRooms
    ) {
        return lotService.filterLots(name, city, address, minArea, maxArea, minFloor, maxFloor, minRooms, maxRooms);
    }

    @Operation(summary = "Create a reservation", description = "Creates a reservation for a specified lot",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Reservation details",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                {
                    "clientName": "Egor Markin",
                    "reservationTime": "2024-07-29T23:44:14.674Z",
                    "lot": { "id": 1 },
                    "contactPhone": "+79123456789"
                }
            """)
                    )
            )
    )
    @PostMapping("/reservations")
    public Reservation bookLot(@Valid @RequestBody Reservation reservation) {
        return reservationService.bookLot(reservation);
    }
}
