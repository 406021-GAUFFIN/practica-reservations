package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.enums.DocumentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.PaymentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.RoomTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String status = "EXITOSA";


    private Double price;

    private Long hotelId;

    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentType;

    private String clientId;
    @Enumerated(EnumType.STRING)

    private RoomTypeEnum roomType;

    private LocalDate entryDate;

    private LocalDate departureDate;
    @Enumerated(EnumType.STRING)

    private PaymentTypeEnum paymentType;


}
