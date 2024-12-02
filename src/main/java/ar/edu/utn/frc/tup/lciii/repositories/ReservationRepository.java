package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.ReservationEntity;
import ar.edu.utn.frc.tup.lciii.enums.RoomTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("SELECT i FROM ReservationEntity i "
            + "WHERE i.hotelId = :hotelId "
            + "AND i.roomType = :roomType "
            + "AND ((i.entryDate between :fromDate and :toDate) or (i.departureDate between :fromDate and :toDate))")
    List<ReservationEntity> getClashingReservations(
            @Param("hotelId") Long hotelId,
            @Param("roomType") RoomTypeEnum roomType,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate);
}
