package Com.hotel.manage.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.hotel.manage.Repository.HotelRepository;
import Com.hotel.manage.Repository.RoomRepository;
import Com.hotel.manage.Service.RoomService;
import Com.hotel.manage.entity.Hotel;
import Com.hotel.manage.entity.Room;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	 @Autowired
	    private HotelRepository hotelRepository;
	
	 @Override
	    public Room addRoom(Room room) {
	        Optional<Hotel> hotelOptional = hotelRepository.findById(room.getHotel().getHotelId());
	        if (hotelOptional.isPresent()) {
	            room.setHotel(hotelOptional.get());
	            return roomRepository.save(room);
	        } else {
	            throw new IllegalArgumentException("Hotel not found with ID: " + room.getHotel().getHotelId());
	        }
	    }
	 @Override
	    public Room updateRoom(int roomId, Room room) {
	        Optional<Room> existingRoomOpt = roomRepository.findById(roomId);
	        if (existingRoomOpt.isPresent()) {
	            Room existingRoom = existingRoomOpt.get();

	            // Ensure that the hotel is correctly associated
	            Optional<Hotel> hotelOptional = hotelRepository.findById(room.getHotel().getHotelId());
	            if (hotelOptional.isPresent()) {
	                existingRoom.setHotel(hotelOptional.get());
	            } else {
	                throw new IllegalArgumentException("Hotel not found with ID: " + room.getHotel().getHotelId());
	            }

	            // Update other fields
	            existingRoom.setRoomNumber(room.getRoomNumber());
	            existingRoom.setType(room.getType());
	            existingRoom.setPricePerNight(room.getPricePerNight());
	            existingRoom.setAvailabilityStatus(room.isAvailabilityStatus());

	            return roomRepository.save(existingRoom);
	        } else {
	            throw new IllegalArgumentException("Room not found with ID: " + roomId);
	        }
	    }
	@Override
	public void deleteRoom(int roomId) {
		roomRepository.deleteById(roomId);
		
	}

	@Override
	public List<Room> getAllRooms() {
		
		
		return roomRepository.findAll();
	}

	@Override
	public Room getRoomById(int roomId) {
		
		return roomRepository.findById(roomId).orElseThrow(()->
		new RuntimeException("Room With Id "+ roomId+ " not found ")
				);
		
	}

	@Override
	public List<Room> getRoomByHotelId(int hotelId) {
		return roomRepository.findByHotel_HotelId(hotelId);
	}

	
	
}
