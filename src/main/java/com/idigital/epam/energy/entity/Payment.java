//package com.idigital.epam.energy.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name ="Payment")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Long id;
//
//    private Integer paymentAmount;
//    private Integer Date;
//    @ManyToMany
//    @JsonIgnore
//    private Billing billing;
//
//}
