package com.idigital.epam.energy.controller;


import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.entity.Role;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.repository.RoleRepository;
import com.idigital.epam.energy.service.AddressService;
import com.idigital.epam.energy.service.HomeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeService homeService;

    @MockBean
    private HomeRepository homeRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private AddressService addressService;

//    @GetMapping("/getAllUsersWithHomes")
//    public ResponseEntity<List<Home>> getAll() throws Exception {
//        homeService.create();
//        // homeService.createInstitutional();
//        List<Home> homes = homeService.getAll();
//        return new ResponseEntity(homes, HttpStatus.OK);
//    }
    @Test
    public void getAllUsersWithHomesTest(){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/getAllUsersWithHomes");
    }
}
