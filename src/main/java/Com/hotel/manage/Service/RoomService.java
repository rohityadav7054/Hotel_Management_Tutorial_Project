package Com.hotel.manage.Service;

import java.util.List;

import Com.hotel.manage.entity.Room;

public interface RoomService {

	Room addRoom(Room room);
	Room updateRoom(int roomId, Room room);
	void deleteRoom(int roomId);
	List<Room> getAllRooms();
	Room getRoomById(int roomId);
	List<Room> getRoomByHotelId( int hotelId);
	
	
	}
