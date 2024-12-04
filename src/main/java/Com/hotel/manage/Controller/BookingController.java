package Com.hotel.manage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Com.hotel.manage.Repository.CustomerRepositry;
import Com.hotel.manage.Repository.HotelRepository;
import Com.hotel.manage.Repository.RoomRepository;
import Com.hotel.manage.Service.BookingService;
import Com.hotel.manage.entity.Booking;
import Com.hotel.manage.entity.Customer;
import Com.hotel.manage.entity.Hotel;
import Com.hotel.manage.entity.Room;



@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired 
    private BookingService bookingService;

    @Autowired
    private CustomerRepositry customerRepository;
    
    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    // Create a new booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        // Fetch related entities by their IDs
        Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElse(null);
        Hotel hotel = hotelRepository.findById(booking.getHotel().getHotelId()).orElse(null);
        Room room = roomRepository.findById(booking.getRoom().getRoomId()).orElse(null);

        // Ensure all related entities exist
        if (customer == null || hotel == null || room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If any entity is not found
        }

        // Set the relationships
        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setRoom(room);
        
        // Create the booking
        Booking createdBooking = bookingService.createBooking(booking);
        
        // Ensure that the related entities' details are included in the response
        createdBooking.setCustomer(customer); // Populate customer details
        createdBooking.setHotel(hotel); // Populate hotel details
        createdBooking.setRoom(room); // Populate room details

        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED); // Return the created booking
    }
    
    // Get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        
        // Populate related entities' details for each booking
        for (Booking booking : bookings) {
            Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElse(null);
            Hotel hotel = hotelRepository.findById(booking.getHotel().getHotelId()).orElse(null);
            Room room = roomRepository.findById(booking.getRoom().getRoomId()).orElse(null);
            
            booking.setCustomer(customer);
            booking.setHotel(hotel);
            booking.setRoom(room);
        }

        return bookings;
    }
    
    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If booking doesn't exist
        }

        // Populate related entities' details for the booking
        Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElse(null);
        Hotel hotel = hotelRepository.findById(booking.getHotel().getHotelId()).orElse(null);
        Room room = roomRepository.findById(booking.getRoom().getRoomId()).orElse(null);
        
        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setRoom(room);
        
        return new ResponseEntity<>(booking, HttpStatus.OK); // Return the populated booking
    }

    // Update booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id, @RequestBody Booking booking) {
        // Fetch related entities by their IDs
        Customer customer = customerRepository.findById(booking.getCustomer().getCustomerId()).orElse(null);
        Hotel hotel = hotelRepository.findById(booking.getHotel().getHotelId()).orElse(null);
        Room room = roomRepository.findById(booking.getRoom().getRoomId()).orElse(null);

        // Ensure all related entities exist
        if (customer == null || hotel == null || room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If any entity is not found
        }

        // Set the relationships
        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setRoom(room);

        // Update the booking
        Booking updatedBooking = bookingService.UpdateBooking(id, booking);
        
        if (updatedBooking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If booking not found
        }

        // Ensure related entities' details are included in the updated booking response
        updatedBooking.setCustomer(customer);
        updatedBooking.setHotel(hotel);
        updatedBooking.setRoom(room);

        return new ResponseEntity<>(updatedBooking, HttpStatus.OK); // Return updated booking
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        if (!isDeleted) {
            return new ResponseEntity<>("Booking not found for deletion.",HttpStatus.NOT_FOUND); // Booking not found for deletion
        }
        return new ResponseEntity<>("Booking successfully deleted.",HttpStatus.NO_CONTENT); // Successfully deleted
    }
}