package com.example.suneet.onroadwheels;

/**
 * Created by suneet on 29/7/17.
 */

class UserProfile {
    private String userName;
    private String imgUrl;
    private String drivingLicenceNo;
    private String bloodGroup;
    private String address;
    private String age;
    private String gender;
    private String status;
    private String occupation;
    private String disease;


    public UserProfile() {

    }

    public UserProfile(String userName, String imgUrl, String drivingLicenceNo, String bloodGroup, String address, String age, String gender, String status, String occupation, String disease) {
        this.userName = userName;
        this.imgUrl = imgUrl;
        this.drivingLicenceNo = drivingLicenceNo;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.occupation = occupation;
        this.disease = disease;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
