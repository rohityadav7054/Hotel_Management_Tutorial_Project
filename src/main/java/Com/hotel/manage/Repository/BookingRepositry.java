package Com.hotel.manage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.hotel.manage.entity.Booking;

@Repository
public interface BookingRepositry extends JpaRepository<Booking, Integer>{

	
}
