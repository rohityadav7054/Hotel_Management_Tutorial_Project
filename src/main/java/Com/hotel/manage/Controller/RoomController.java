package Com.hotel.manage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Com.hotel.manage.Service.RoomService;
import Com.hotel.manage.entity.Room;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        try {
            Room savedRoom = roomService.addRoom(room);
            return ResponseEntity.ok(savedRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
	  @PutMapping("/{roomId}")
	    public ResponseEntity<Room> updateRoom(@PathVariable("roomId") int roomId, @RequestBody Room room) {
	        try {
	            Room updatedRoom = roomService.updateRoom(roomId, room);
	            return ResponseEntity.ok(updatedRoom);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	    }
	
	@DeleteMapping("/{roomId}")
	public String deleteRoom(@PathVariable int roomId) {
		roomService.deleteRoom(roomId);
		return "Room with ID "+roomId+" deleted successfully.";
		}

		@GetMapping
		public List<Room> getAllRooms(){
			return roomService.getAllRooms();
		}
	
		@GetMapping("/{roomId}")
		public Room getRoomById(@PathVariable int roomId) {
			return roomService.getRoomById(roomId);
		}
	
		@GetMapping("/hotel/{hotelId}")
		public List<Room> getRoomsByHotelID(@PathVariable int hotelId)
		{
			return roomService.getRoomByHotelId(hotelId);
		}
}

	
	