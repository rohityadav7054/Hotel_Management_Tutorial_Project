package Com.hotel.manage.Service;

import java.util.List;
import Com.hotel.manage.entity.Hotel;

public interface HotelService {

	 Hotel addHotel(Hotel hotel);
	 Hotel updateHotel(int hotelid , Hotel hotel);
	 void deleteHotel(int hotelId);
	 List<Hotel> getAllHotels();
	 Hotel getHotelById(int hotelId);
	 
}
