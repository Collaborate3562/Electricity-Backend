package com.idigital.epam.energy.service.DTO;

public class ResponseResident {
    private Resident result;
    private Boolean success;


    public ResponseResident() {
    }

    public Resident getResult() {
        return result;
    }

    public void setResult(Resident result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
