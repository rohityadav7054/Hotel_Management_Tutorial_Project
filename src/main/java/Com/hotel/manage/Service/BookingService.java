package Com.hotel.manage.Service;

import java.util.List;

import Com.hotel.manage.entity.Booking;

public interface BookingService {

	Booking createBooking(Booking booking);
	List<Booking> getAllBookings();
	Booking getBookingById(int bookingId);
	Booking UpdateBooking(int bookingId,Booking UpdateBooking);
	boolean deleteBooking(int bookingId);
	
}
