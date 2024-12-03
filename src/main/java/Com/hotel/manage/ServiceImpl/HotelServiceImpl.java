package Com.hotel.manage.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.hotel.manage.Repository.HotelRepository;
import Com.hotel.manage.Service.HotelService;
import Com.hotel.manage.entity.Hotel;

@Service
public class HotelServiceImpl implements HotelService{

@Autowired
private HotelRepository hotelRepository;

@Override
public Hotel addHotel(Hotel hotel) {
	return hotelRepository.save(hotel);
}


@Override
public Hotel updateHotel(int hotelid, Hotel hotel) {
Optional<Hotel> existingHotel = hotelRepository.findById(hotelid);

	if(existingHotel.isPresent()) {
		Hotel updatedHotel = existingHotel.get();
		updatedHotel.setName(hotel.getName());
		updatedHotel.setAddress(hotel.getAddress());
		updatedHotel.setCity(hotel.getCity());
		updatedHotel.setState(hotel.getState());
		updatedHotel.setZipCode(hotel.getZipCode());
		updatedHotel.setPhoneNumber(hotel.getPhoneNumber());
		updatedHotel.setEmail(hotel.getEmail());
		return hotelRepository.save(updatedHotel);
	}
throw new RuntimeException("Hotel with ID" +hotelid +" not found");
}


@Override
public void deleteHotel(int hotelId) {

hotelRepository.deleteById(hotelId);	
}


@Override
public List<Hotel> getAllHotels() {
	return hotelRepository.findAll();
}


@Override
public Hotel getHotelById(int hotelId) {
	return hotelRepository.findById(hotelId).orElseThrow(()->
	new RuntimeException("Hotel with Id "+hotelId+" not found")
			);
}
	
}
