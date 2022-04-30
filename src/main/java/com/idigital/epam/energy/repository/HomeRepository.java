package com.idigital.epam.energy.repository;

import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.service.DTO.HomeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    public Optional<Home> findByHomeCode(Long homeCode);

    @Query("select new com.idigital.epam.energy.service.DTO.HomeResponse(h.id," +
            "h.address.homeNumber,h.homeCode,h.user.cardNumber, h.address.district,h.address.street,h.buildingType) from Home h join h.user u on h.user.id=u.id where h.user.cardNumber=?1")
    List<HomeResponse> findHomeListByUserId(String card);

    @Query("select new com.idigital.epam.energy.service.DTO.HomeResponse(h.id," +
            "h.address.homeNumber,h.homeCode,h.user.cardNumber, h.address.district,h.address.street,h.buildingType) from Home h")
    List<HomeResponse> getAllHoMes();

//    List<Home> findHomesByUserId(Long id);
}
