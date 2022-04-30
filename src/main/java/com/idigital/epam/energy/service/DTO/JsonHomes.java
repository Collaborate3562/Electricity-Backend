package com.idigital.epam.energy.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonHomes {
    private Long cardNumber;
    List<HomeDto> homes;


}
