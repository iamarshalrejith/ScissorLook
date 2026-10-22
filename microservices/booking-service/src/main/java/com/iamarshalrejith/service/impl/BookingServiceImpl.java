package com.iamarshalrejith.service.impl;

import com.iamarshalrejith.domain.BookingStatus;
import com.iamarshalrejith.modal.Booking;
import com.iamarshalrejith.modal.SalonReport;
import com.iamarshalrejith.payload.dto.BookingRequest;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.ServiceDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.repository.BookingRepository;
import com.iamarshalrejith.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet) throws Exception {
        int totalDuration = serviceDTOSet.stream().mapToInt(ServiceDTO::getDuration).sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        isTimeSlotAvailable(salon,bookingStartTime,bookingEndTime);

        int totalPrice = serviceDTOSet.stream().mapToInt(ServiceDTO::getPrice).sum();
        Set<Long> idList = serviceDTOSet.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }

    public void isTimeSlotAvailable(SalonDTO salonDTO, LocalDateTime bookingStartTime,LocalDateTime bookingEndTime) throws Exception{
        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        if(!bookingStartTime.isBefore(bookingEndTime)){
            throw new Exception("Booking start time must be before end time.");
        }

        if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)){
            throw new Exception("Booking time must be within salon's working hours");
        }


        List<Booking> existingBookings = getBookingsBySalon(salonDTO.getId());
        for(Booking existingBooking : existingBookings){
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("Slot not available, choose different time.");
            }


        }
    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if(booking == null){
            throw new Exception("booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = getBookingById(bookingId);

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        List<Booking> allBookings = getBookingsBySalon(salonId);

        if(date == null){
            return allBookings;
        }

        return allBookings.stream().filter(booking -> isSameDate(booking.getStartTime(),date) || isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
       List<Booking> bookings = getBookingsBySalon(salonId);

       int totalEarnings = bookings.stream().mapToInt(Booking::getTotalPrice).sum();
       Integer totalBooking = bookings.size();

       List<Booking> cancelledBookings = bookings.stream().filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED)).collect(Collectors.toList());

       Double totalRefund = cancelledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

       SalonReport salonReport = new SalonReport();
       salonReport.setSalonId(salonId);
       salonReport.setCancelledBookings(cancelledBookings.size());
       salonReport.setTotalBookings(totalBooking);
       salonReport.setTotalEarnings(totalEarnings);
       salonReport.setTotalRefund(totalRefund);

       return salonReport;
    }
}