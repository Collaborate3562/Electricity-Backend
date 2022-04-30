package com.idigital.epam.energy.controller;

import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.hmac.HMACUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/panel")
public class AdminLoginController {

	@Autowired
	private HMACUtils hmACUtils;

	@GetMapping
	public ResponseEntity<?> adminTest() throws URISyntaxException {
		return hmACUtils.getRequestWithHmac("ENERGY", "get_homes", "http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/request/homesWithOwner", "energyKey");
	}
	@PostMapping("/payment")
	public ResponseEntity<?> adminTest2() throws Exception {
		return hmACUtils.postRequestWithHmac("ENERGY", "payment", "http://54.236.248.165/api/v1/pay-notification", "energyKey","payed");
	}

	}





