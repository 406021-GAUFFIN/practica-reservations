package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CreateReservationDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.common.ReservationDTO;
import ar.edu.utn.frc.tup.lciii.servicies.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping("/reservation/{id_reserva}")
    public ResponseEntity<ReservationDTO> getReservartion(@PathVariable Long id_reserva) {
        return ResponseEntity.ok(reservationService.getReservation(id_reserva));
    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody CreateReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTO));
    }
}
