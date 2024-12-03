package Com.hotel.manage.Service;

import java.util.List;
import java.util.Optional;

import Com.hotel.manage.entity.Customer;

public interface CustomerService {
	
	String registerCustomer(Customer customer);
	
	String LoginCustomer(String email , String Password);
	
	Optional<Customer> getCustomerByEmail(String email);
	
	List<Customer> getAllCustomers();
	
}
