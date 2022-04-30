package com.idigital.epam.energy.service.DTO;

public class Parents {
    private Person mother;
    private Person father;

    public Parents() {
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }
}
