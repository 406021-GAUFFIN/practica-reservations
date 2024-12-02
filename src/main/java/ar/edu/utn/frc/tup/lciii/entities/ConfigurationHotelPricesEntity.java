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
@Table(name = "configuration_hotel_prices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationHotelPricesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    private Long hotelId;

    private RoomTypeEnum roomType;




}
