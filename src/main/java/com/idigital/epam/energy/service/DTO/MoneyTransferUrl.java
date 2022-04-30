package com.idigital.epam.energy.service.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString


public class MoneyTransferUrl {

    private boolean success;
    private String message;
    private String object;

}
