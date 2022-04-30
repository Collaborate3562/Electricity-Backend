package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.service.DTO.HomeResponse;
import com.idigital.epam.energy.service.DTO.Result;
import org.json.JSONArray;

import java.util.List;

public interface HomeService extends CommonService<Home>{

    Home create() throws Exception;
    Result getHomesByCardNumber(Long cardNum);
    Home createInstitutional() throws Exception;
    List<HomeResponse> getHomesList();

}
