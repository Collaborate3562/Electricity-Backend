package com.idigital.epam.energy.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"home_id"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnergyMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    private Home home;

    private Integer energyConsumption;

    private Integer previousReading;


}

