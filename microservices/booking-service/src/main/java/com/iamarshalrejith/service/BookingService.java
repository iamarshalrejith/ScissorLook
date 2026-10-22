package com.iamarshalrejith.service;

import com.iamarshalrejith.domain.BookingStatus;
import com.iamarshalrejith.modal.Booking;
import com.iamarshalrejith.modal.SalonReport;
import com.iamarshalrejith.payload.dto.BookingRequest;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.ServiceDTO;
import com.iamarshalrejith.payload.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet) throws Exception;
    List<Booking> getBookingsByCustomerId(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
