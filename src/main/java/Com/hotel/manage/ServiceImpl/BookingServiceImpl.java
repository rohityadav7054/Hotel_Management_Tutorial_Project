package Com.hotel.manage.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.hotel.manage.Repository.BookingRepositry;
import Com.hotel.manage.Service.BookingService;
import Com.hotel.manage.entity.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepositry bookingRepository;

	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking getBookingById(int bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking with"+bookingId+" not found"));
	}

	@Override
	public Booking UpdateBooking(int bookingId, Booking UpdateBooking) {
		Booking existingBooking = getBookingById(bookingId);
		existingBooking.setCustomer(UpdateBooking.getCustomer());
		existingBooking.setHotel(UpdateBooking.getHotel());
		existingBooking.setRoom(UpdateBooking.getRoom());
		existingBooking.setCheckInDate(UpdateBooking.getCheckInDate());
		existingBooking.setCheckOutDate(UpdateBooking.getCheckOutDate());
		existingBooking.setTotalAmount(UpdateBooking.getTotalAmount());
		existingBooking.setPaymentStatus(UpdateBooking.getPaymentStatus());
		return bookingRepository.save(existingBooking);
		
	}

	@Override
	 public boolean deleteBooking(int id) {
	        Optional<Booking> booking = bookingRepository.findById(id);
	        if (booking.isPresent()) {
	            bookingRepository.deleteById(id);
	            return true;
	        }
	        return false; // Return false if the booking doesn't exist
	    }
	
}
