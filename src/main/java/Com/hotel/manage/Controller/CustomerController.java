package Com.hotel.manage.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.hotel.manage.Service.CustomerService;
import Com.hotel.manage.entity.Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		 	String response = customerService.registerCustomer(customer);
			return ResponseEntity.ok(response);
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginCustomer(@RequestParam String email, @RequestParam String password){
			String response = customerService.LoginCustomer(email,password);
	 		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
		
		Optional<Customer> customer = customerService.getCustomerByEmail(email);
		return customer.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer(){
		
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}
	
	
}
