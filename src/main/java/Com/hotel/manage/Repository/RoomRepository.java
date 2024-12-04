package Com.hotel.manage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.hotel.manage.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
			
		List<Room> findByHotel_HotelId(int hotelId);
}
