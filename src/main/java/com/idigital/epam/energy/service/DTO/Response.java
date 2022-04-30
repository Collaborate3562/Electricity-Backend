package com.idigital.epam.energy.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private List<Result> result;
    private Boolean success;


}
