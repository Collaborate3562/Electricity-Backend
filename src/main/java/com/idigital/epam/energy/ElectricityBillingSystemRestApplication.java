package com.idigital.epam.energy;

import com.idigital.epam.energy.entity.Role;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.repository.RoleRepository;
import com.idigital.epam.energy.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableAsync
@EnableSwagger2

public class ElectricityBillingSystemRestApplication {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	InitializingBean dataLoader(){
		return () ->{
			List<Role> roles = new ArrayList<>();
			roleRepository.save(new Role("ROLE_ADMIN"));
			roleRepository.save(new Role("ROLE_USER"));

			User user = new User();
			user.setCardNumber("65498732111");
			user.setLastName("Universe");
			user.setActive(Boolean.TRUE);

			user.setFirstName("Universe");
			user.setPassword(passwordEncoder.encode("universe_123"));
			Set<Role> roleAdminAdmin = new HashSet<>();
			roleAdminAdmin.add(roleRepository.findByName("ROLE_ADMIN"));
			user.setRole(roleAdminAdmin);
			userRepository.save(user);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ElectricityBillingSystemRestApplication.class, args);
	}
}






























//	public static final Contact DEFAULT_CONTACT = new Contact(
//			"JFSD_17", "http://localhost:3000/", "xxx@gmail.com");
//	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
//			"Electricity-Billing-System", "API Description", "1.0",
//			"urn:tos", DEFAULT_CONTACT,
//			"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
//
//	/**
//	 * @return Docket
//	 */
//	@Bean
//	public Docket openApiCustomerStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo(DEFAULT_API_INFO)
//				.groupName("open-api-customer-store")
//				.select()
//				.paths(customerPaths())
//				.build();
//	}
//
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
//
//	private Predicate<String> customerPaths() {
//		return regex(".*/api/customer/.*");
//	}
//
//	//payment
//	@Bean
//	public Docket openApiPaymentStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo(DEFAULT_API_INFO)
//				.groupName("open-api-payment-store")
//				.select()
//				.paths(paymentPaths())
//				.build();
//	}
//
//	private Predicate<String> paymentPaths() {
//		return regex(".*/api/payment/.*");
//	}
//
//	//admin
//	@Bean
//	public Docket openApiAdminStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo(DEFAULT_API_INFO)
//				.groupName("open-api-admin-store")
//				.select()
//				.paths(adminPaths())
//				.build();
//	}
//
//	private Predicate<String> adminPaths() {
//		return regex(".*/admin/.*");
//	}
//
//	//CustomerLogin
//	@Bean
//	public Docket openApiCustomerLoginStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo(DEFAULT_API_INFO)
//				.groupName("open-api-customerLogin-store")
//				.select()
//				.paths(customerLoginPaths())
//				.build();
//	}
//
//	private Predicate<String> customerLoginPaths() {
//		return regex(".*/api/customerLogin/.*");
//	}
//
//	//AdminLogin
//	@Bean
//	public Docket openApiAdminLoginStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo(DEFAULT_API_INFO)
//				.groupName("open-api-adminLogin-store")
//				.select()
//				.paths(adminLoginPaths())
//				.build();
//	}
//
//	private Predicate<String> adminLoginPaths() {
//		return regex(".*/api/adminLogin/.*");
//	}
//
//	//bill
//	@Bean
//	public Docket openApiBillStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo(DEFAULT_API_INFO)
//				.groupName("open-api-bill-store")
//				.select()
//				.paths(billPaths())
//				.build();
//	}
//
//	/**
//	 * @return regex
//	 */
//
//	private Predicate<String> billPaths() {
//		return regex(".*/api/billDetails/.*");
//	}
//}
//	@Bean
//	public Docket openApiForgotPasswordStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo (DEFAULT_API_INFO)
//				.groupName("open-api-ForgotPassword-store")
//				.select()
//				.paths(forgotPasswordPaths())
//				.build();
//	}
//	/**
//	 *
//	 * @return regex
//	 */
//
//	private Predicate<String> forgotPasswordPaths() {
//		return regex(".*/api/forgotPassword/.*");
//	}
//

	/**
	 * @return Docket
	 */
//
//	@Bean
//	public Docket openApiAdminForgotPasswordStore() {
//		return new Docket(DocumentationType.OAS_30)
//				.apiInfo (DEFAULT_API_INFO)
//				.groupName("open-api-AdminForgotPassword-store")
//				.select()
//				.paths(adminForgotPasswordPaths())
//				.build();
//	}
//	/**
//	 *
//	 * @return regex
//	 */
//
//	private Predicate<String> adminForgotPasswordPaths() {
//		return regex(".*/api/AdminForgotPassword/.*");
//	}
//
//
//
//}
