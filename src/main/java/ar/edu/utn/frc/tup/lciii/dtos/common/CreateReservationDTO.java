package ar.edu.utn.frc.tup.lciii.dtos.common;


import ar.edu.utn.frc.tup.lciii.enums.DocumentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.PaymentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.RoomTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateReservationDTO {


    @JsonProperty("id_hotel")
    private Long hotelId;

    @JsonProperty("tipo_documento")
    private DocumentTypeEnum documentType;
    @JsonProperty("id_cliente")
    private String clientId;

    @JsonProperty("tipo_habitacion")
    private RoomTypeEnum roomType;

    @JsonProperty("fecha_ingreso")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate entryDate;


    @JsonProperty("fecha_salida")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate departureDate;

    @JsonProperty("medio_pago")
    private PaymentTypeEnum paymentType;




}
