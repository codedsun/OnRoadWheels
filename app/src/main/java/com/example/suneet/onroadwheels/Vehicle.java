package com.example.suneet.onroadwheels;

/**
 * Created by suneet on 29/7/17.
 */

class Vehicle {
    private String vehicleNo;
    private String vechileModel;
    private String vechileType;

    public Vehicle(String vehicleNo, String vechileModel, String vechileType) {
        this.vehicleNo = vehicleNo;
        this.vechileModel = vechileModel;
        this.vechileType = vechileType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVechileModel() {
        return vechileModel;
    }

    public void setVechileModel(String vechileModel) {
        this.vechileModel = vechileModel;
    }

    public String getVechileType() {
        return vechileType;
    }

    public void setVechileType(String vechileType) {
        this.vechileType = vechileType;
    }
}
