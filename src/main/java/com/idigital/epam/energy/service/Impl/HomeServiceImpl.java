package com.idigital.epam.energy.service.Impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.repository.AddressRepository;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.repository.UserRepository;
import com.idigital.epam.energy.service.AddressService;
import com.idigital.epam.energy.service.CommonService;
import com.idigital.epam.energy.service.DTO.*;

import com.idigital.epam.energy.service.HomeService;
import com.idigital.epam.energy.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service

public class HomeServiceImpl implements HomeService, CommonService<Home> {

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    HMACUtilsService hmACUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressService addressService;


//    @EventListener(ApplicationReadyEvent.class)
//    public void createHome() throws Exception {
//        this.create();
//
//    }


    @Override
    public List<Home> getAll() {
        return homeRepository.findAll();
    }
    @Override
    public List<HomeResponse> getHomesList(){
        return homeRepository.getAllHoMes();
    }

    @Override
    public Home getById(Long id) {
        return Optional.of(homeRepository.findById(id)).filter(Optional::isPresent).get().get();
    }



    @Override
    public Home create() throws Exception{
        String aas =  hmACUtils.getRequestWithHmac("ENERGY", "get_homes", "http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/request/homesWithOwner", "energyKey").getBody().toString();
        ObjectMapper mapper = new ObjectMapper();
        Response json = mapper.readValue(aas, Response.class);


        Home home;
        Address address;
        List<Result> homes = json.getResult();
        User user;
        for (Result result: homes) {

            user = userService.create(result.getCardNumber(),String.valueOf(result.getCardNumber()));

            for (HomeDto homeDto: result.getHomes()){
               home = new Home();
               home.setHomeCode(homeDto.getHomeCode());
               home.setBuildingType(BuildingType.RESIDENTIAL);
               address = new Address();
               address.setHomeNumber(homeDto.getAddress().getHomeNumber());
               address.setDistrict(homeDto.getAddress().getDistrict());
               address.setStreet(homeDto.getAddress().getStreet());
               address = addressService.createAddress(address);
               home.setAddress(address);
               if (user!=null){
                   home.setUser(user);
               }else {
                   home.setUser(userService.getCurrentUser());
               }
               homeRepository.save(home);
           }
        }
        return null;
    }

    @Override
    public Home createInstitutional() throws Exception {

        String aas =  hmACUtils.getRequestWithHmac("ENERGY", "get_homes", "http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/request/homesWithOwner", "energyKey").getBody().toString();

        ObjectMapper mapper = new ObjectMapper();
        Response json = mapper.readValue(aas, Response.class);

        Home home;
        Address address;
        List<Result> homes = json.getResult();
        User user;
        for (Result result: homes) {
//            if (!userRepository.existsById(userRepository.findUserByCardNumber(String.valueOf(result.getCardNumber())).get().getId())){
                user = userService.create(result.getCardNumber(),String.valueOf(result.getCardNumber()));
//            }else {
//                user = userRepository.findUserByCardNumber(String.valueOf(result.getCardNumber())).get();
//            }
            for (HomeDto homeDto: result.getHomes()){
                home = new Home();
                home.setHomeCode(homeDto.getHomeCode());
                home.setBuildingType(BuildingType.INSTITUTIONAL);
                address = new Address();
                address.setHomeNumber(homeDto.getAddress().getHomeNumber());
                address.setDistrict(homeDto.getAddress().getDistrict());
                address.setStreet(homeDto.getAddress().getStreet());
                address = addressService.createAddress(address);
                home.setAddress(address);
                home.setUser(user);
                homeRepository.save(home);
            }
        }
        return null;
    }


    @Override
    public Home update(Home data) throws Exception{


        if(data.getId() != null){
            Home newHome = homeRepository.findById(data.getId()).get();
            newHome.setHomeCode(data.homeCode);
            newHome.setBuildingType(data.getBuildingType());
            newHome.setAddress(data.getAddress());
            newHome.setUser(data.getUser());
            return homeRepository.save(data);
        }
        throw new Exception("Id shouldn't be null");
    }

    @Override
    public void delete(Home data) {
        homeRepository.deleteById(data.getId());
    }

    public Result getHomesByCardNumber(Long cardNumber){
        Result result = new Result();
        return result;
    }



    public List<HomeDto> parser(List<Home> homes){
        List<HomeDto> homeDtos = new LinkedList<>();
        HomeDto h;
        for (Home home : homes) {
            h = new HomeDto();
            h.setHomeCode(home.getHomeCode());
            h.setAddress(home.getAddress());
            homeDtos.add(h);
        }
        return homeDtos;
    }
}







