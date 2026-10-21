package com.iamarshalrejith.service;

import com.iamarshalrejith.domain.BookingStatus;
import com.iamarshalrejith.modal.Booking;
import com.iamarshalrejith.modal.SalonReport;
import com.iamarshalrejith.payload.dto.BookingRequest;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.ServiceDTO;
import com.iamarshalrejith.payload.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet);
    List<Booking> getBookingsByCustomerId(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id);
    Booking updateBooking(Long bookingId, BookingStatus status);
    List<Booking> getBookingsByDate(LocalDateTime date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
