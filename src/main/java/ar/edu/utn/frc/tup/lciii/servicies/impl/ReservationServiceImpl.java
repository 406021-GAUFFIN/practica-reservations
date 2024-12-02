package ar.edu.utn.frc.tup.lciii.servicies.impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.CreateReservationDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ReservationDTO;
import ar.edu.utn.frc.tup.lciii.entities.ReservationEntity;
import ar.edu.utn.frc.tup.lciii.enums.DocumentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.PaymentTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.RoomTypeEnum;
import ar.edu.utn.frc.tup.lciii.enums.SeasonEnum;
import ar.edu.utn.frc.tup.lciii.repositories.ReservationRepository;
import ar.edu.utn.frc.tup.lciii.servicies.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    
    private final ModelMapper modelMapper;


    private final Map<Month, SeasonEnum> seasonbyMonth;

    private final Map<SeasonEnum, Double> discountBySeason;

    private final Map<PaymentTypeEnum, Double> discountByPaymentType;


    private final Map<Long, Map<RoomTypeEnum, Double>> pricesByHotelAndRoom;




    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;

        seasonbyMonth = new HashMap<>();
        seasonbyMonth.put(Month.JANUARY, SeasonEnum.HIGH);
        seasonbyMonth.put(Month.FEBRUARY, SeasonEnum.HIGH);
        seasonbyMonth.put(Month.JULY, SeasonEnum.HIGH);
        seasonbyMonth.put(Month.AUGUST, SeasonEnum.HIGH);
        seasonbyMonth.put(Month.MARCH, SeasonEnum.LOW);
        seasonbyMonth.put(Month.APRIL, SeasonEnum.LOW);
        seasonbyMonth.put(Month.OCTOBER, SeasonEnum.LOW);
        seasonbyMonth.put(Month.NOVEMBER, SeasonEnum.LOW);
        seasonbyMonth.put(Month.JUNE, SeasonEnum.MEDIUM);
        seasonbyMonth.put(Month.SEPTEMBER, SeasonEnum.MEDIUM);
        seasonbyMonth.put(Month.DECEMBER, SeasonEnum.MEDIUM);

        discountBySeason = new HashMap<>();
        discountBySeason.put(SeasonEnum.HIGH, 1.3);
        discountBySeason.put(SeasonEnum.LOW, 0.9);
        discountBySeason.put(SeasonEnum.MEDIUM, 1.0);



        discountByPaymentType = new HashMap<>();
        discountByPaymentType.put(PaymentTypeEnum.EFECTIVO, 0.75);
        discountByPaymentType.put(PaymentTypeEnum.TARJETA_DEBITO, 0.90);
        discountByPaymentType.put(PaymentTypeEnum.TARJETA_CREDITO, 1.0);


        pricesByHotelAndRoom = new HashMap<>();

        Map<RoomTypeEnum, Double> pricesForHotel1 = new HashMap<>();
        pricesForHotel1.put(RoomTypeEnum.SIMPLE, 1250.0);
        pricesForHotel1.put(RoomTypeEnum.DOBLE, 2100.0);
        pricesForHotel1.put(RoomTypeEnum.TRIPLE, 2850.0);
        pricesByHotelAndRoom.put(1L, pricesForHotel1);


        Map<RoomTypeEnum, Double> pricesForHotel2 = new HashMap<>();
        pricesForHotel2.put(RoomTypeEnum.SIMPLE, 370.0);
        pricesForHotel2.put(RoomTypeEnum.DOBLE, 650.0);
        pricesForHotel2.put(RoomTypeEnum.TRIPLE, 875.0);
        pricesByHotelAndRoom.put(2L, pricesForHotel2);

        Map<RoomTypeEnum, Double> pricesForHotel3 = new HashMap<>();
        pricesForHotel3.put(RoomTypeEnum.SIMPLE, 2200.0);
        pricesForHotel3.put(RoomTypeEnum.DOBLE, 3700.0);
        pricesForHotel3.put(RoomTypeEnum.TRIPLE, 4100.0);
        pricesByHotelAndRoom.put(2L, pricesForHotel3);





    }

    @Override
    public ReservationDTO getReservation(Long id) {

        ReservationEntity reservationEntity = reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(reservationEntity, ReservationDTO.class);
    }

    @Override
    public ReservationDTO createReservation(CreateReservationDTO reservationDTO) {
        
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setClientId(reservationDTO.getClientId());
        reservationEntity.setDocumentType(reservationDTO.getDocumentType());
        reservationEntity.setHotelId(reservationDTO.getHotelId());
        reservationEntity.setEntryDate(reservationDTO.getEntryDate());
        reservationEntity.setDepartureDate(reservationDTO.getDepartureDate());
        reservationEntity.setPaymentType(reservationDTO.getPaymentType());
        reservationEntity.setRoomType(reservationDTO.getRoomType());


        if(!reservationRepository.getClashingReservations(reservationDTO.getHotelId(), reservationDTO.getRoomType(), reservationDTO.getEntryDate(), reservationDTO.getDepartureDate()).isEmpty()){
            throw new Error("Reservation already in room");
        }


        Double multiplier = 1.0;

        multiplier = multiplier * discountBySeason.get(seasonbyMonth.get(reservationDTO.getEntryDate().getMonth()));
        multiplier = multiplier * discountByPaymentType.get(reservationDTO.getPaymentType());


        if(reservationDTO.getDocumentType().equals(DocumentTypeEnum.CUIT)){
            if(seasonbyMonth.get(reservationDTO.getEntryDate().getMonth()).equals(SeasonEnum.LOW)){
                multiplier = multiplier * 0.85;
            }else{
                multiplier = multiplier * 0.9;
            }
        }else if (seasonbyMonth.get(reservationDTO.getEntryDate().getMonth()).equals(SeasonEnum.LOW)){
            multiplier = multiplier*0.9;
        }



            reservationEntity.setPrice(multiplier * pricesByHotelAndRoom.get(reservationDTO.getHotelId()).get(reservationDTO.getRoomType()));

        return modelMapper.map(reservationRepository.save(reservationEntity), ReservationDTO.class);
        
    }
}
