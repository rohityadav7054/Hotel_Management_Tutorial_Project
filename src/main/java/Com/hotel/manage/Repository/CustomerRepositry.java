package Com.hotel.manage.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Com.hotel.manage.entity.Customer;

public interface CustomerRepositry extends JpaRepository<Customer, Integer>{
		 Optional<Customer> findByEmail(String email);
	}

