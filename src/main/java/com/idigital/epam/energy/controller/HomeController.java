package com.idigital.epam.energy.controller;

import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.service.DTO.HomeResponse;
import com.idigital.epam.energy.service.HomeService;
import com.idigital.epam.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/home")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeController {

    @Autowired
    HomeService homeService;
    @Autowired
    HomeRepository homeRepository;

    @Autowired
    UserService userService;


    @GetMapping("/getAllUsersWithHomes")
    public ResponseEntity<List<Home>> getAll() throws Exception {
        homeService.create();
       //homeService.createInstitutional();
        List<Home> homes = homeService.getAll();
        return new ResponseEntity(homes, HttpStatus.OK);
    }


    @GetMapping("/getHomesWithCurrentUser")
    public ResponseEntity getResultByCardNumber(){
        String card = userService.getCurrentUser().getCardNumber();
        return ResponseEntity.ok(homeRepository.findHomeListByUserId(card));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HomeResponse>> getHomesList(){
        return ResponseEntity.ok(homeService.getHomesList());
    }

    @GetMapping("/getInstitutional")
    public ResponseEntity<List<Home>> getInstitutional() throws Exception {
        homeService.createInstitutional();
        List<Home> homes = homeService.getAll();
        return new ResponseEntity(homes, HttpStatus.OK);
    }



}






//
//    @GetMapping("/{id}")
//    public  ResponseEntity<Home>getHomeById(@PathVariable Long id){
//        Home home = homeService.getById(id);
//        if(home !=null){
//            return new ResponseEntity<>(home,HttpStatus.OK);
//        }else {
//            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PutMapping("/")
//    public ResponseEntity<Home> updateHome(@RequestBody Home home){
//        try {
//            Home updatedHome=homeService.update(home);
//            return new ResponseEntity<>(updatedHome,HttpStatus.OK);
//        }catch(Exception homeException)
//        {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,homeException.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        homeRepository.deleteById(id);
//    }
//
//
//
//
//
//
//    }






