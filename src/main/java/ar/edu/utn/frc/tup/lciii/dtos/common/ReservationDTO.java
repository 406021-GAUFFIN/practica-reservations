package ar.edu.utn.frc.tup.lciii.dtos.common;


import ar.edu.utn.frc.tup.lciii.enums.DocumentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.PaymentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.RoomTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO extends CreateReservationDTO{


    @JsonProperty("id_reserva")
    private Long id;

    @JsonProperty("estado_reserva")
    private String status;


    @JsonProperty("precio")
    private Double price;




}
