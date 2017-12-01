package com.myapp.spring.web;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.myapp.spring.model.CustomerEntity;
import com.myapp.spring.model.OrderEntity;
import com.myapp.spring.service.OrderService;

@RestController
public class OrderRestAPI {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;

	// http://localhost:8080/context/orderHome
	@GetMapping("/orders")
	public ResponseEntity<List<OrderEntity>> getOrders() {
		return new ResponseEntity<List<OrderEntity>>(orderService.getOrderDetails(), HttpStatus.OK);
	}

	@PostMapping("/customers")
	public ResponseEntity<String> addNewCustomers(@RequestBody List<CustomerEntity> customers) {
		orderService.save(customers);
		return new ResponseEntity<String>("Customer Added", HttpStatus.CREATED);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerEntity> findCustomerByid(@PathVariable("id") int id,
			@RequestBody CustomerEntity customerEntity) throws IllegalAccessException, InvocationTargetException {

		orderService.updateCustomer(id, customerEntity);
		return new ResponseEntity<CustomerEntity>(customerEntity, HttpStatus.OK);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerEntity> findCustomer(@PathVariable("id") int id) {
		return new ResponseEntity<CustomerEntity>(orderService.findCustomer(id), HttpStatus.OK);
	}

	@GetMapping(value = "/orderspdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> orderReports() throws DocumentException {

		ByteArrayInputStream bis = OrdersPdfReport.ordersReport(orderService.getOrderDetails());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=orders.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	@GetMapping("/tokens")
	public ResponseEntity<String> getAccessToken() {
		String token = oAuth2ClientContext.getAccessToken().getValue() + " "
				+ oAuth2ClientContext.getAccessToken().getTokenType() + " "
				+ oAuth2ClientContext.getAccessToken().getExpiresIn();

		return ResponseEntity.ok().body(token);
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletRequest req) {
		String auth = req.getHeader("Authorization");
		if(auth!=null && auth.contains("Bearer"))
		{
			String tokenId = auth.substring("Bearer".length()+1);
			OAuth2AccessToken accessToken= tokenStore.readAccessToken(tokenId);
		tokenStore.removeAccessToken(accessToken);	
		}
	}
}
