package ar.edu.utn.frc.tup.lciii.servicies;

import ar.edu.utn.frc.tup.lciii.dtos.common.CreateReservationDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    ReservationDTO getReservation(Long id);


    ReservationDTO createReservation(CreateReservationDTO reservationDTO);


}
