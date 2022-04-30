package com.idigital.epam.energy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idigital.epam.energy.enums.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="home")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    public Long homeCode;

    public BuildingType buildingType;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "home"}, allowSetters = true)
    private Address address;

    @ManyToOne
    private User user;

}

