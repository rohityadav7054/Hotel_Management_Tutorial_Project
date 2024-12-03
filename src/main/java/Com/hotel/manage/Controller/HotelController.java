package Com.hotel.manage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Com.hotel.manage.Service.HotelService;
import Com.hotel.manage.entity.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping()
	public Hotel addHotel(@RequestBody Hotel hotel) {
		return hotelService.addHotel(hotel);
	}
		
	@PutMapping("/{hotelId}")
	public Hotel updateHotel(@PathVariable int hotelId,@RequestBody Hotel hotel) {
		return hotelService.updateHotel(hotelId, hotel);
	}
	
	@DeleteMapping("/{hotelId}")
	public String deleteHotel(@PathVariable int hotelId){
		hotelService.deleteHotel(hotelId);
		return "Hotel with ID" + hotelId+ " deleted successfully";
	}
	@GetMapping("/{hotelId}")
	public Hotel getHotelById(@PathVariable int hotelId) {
		return hotelService.getHotelById(hotelId);
	}
	@GetMapping()
	public List<Hotel> getAllHotel() {
		return hotelService.getAllHotels();
	}

}
