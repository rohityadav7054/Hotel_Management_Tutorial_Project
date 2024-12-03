package Com.hotel.manage.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.hotel.manage.Repository.CustomerRepositry;
import Com.hotel.manage.Service.CustomerService;
import Com.hotel.manage.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepositry customerRepository;

	@Override
	public String registerCustomer(Customer customer) {
		if(customerRepository.findByEmail(customer.getEmail()).isPresent()) {
		return "Customer with this email already exists!";
		}
		customerRepository.save(customer);
		return "Customer registered successfully";
	}

	@Override
	public String LoginCustomer(String email, String Password) {
Optional<Customer> customer = customerRepository.findByEmail(email);
if(customer.isPresent() && customer.get().getPassword().equals(Password)) {
	return "Login successful !";
}
		return "Invalid credentials ! please regsiter if you don't have an account.";
	}

	@Override
	public Optional<Customer> getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
}
