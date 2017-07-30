package com.example.suneet.onroadwheels;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suneet on 29/7/17.
 */

public class User {
    UserProfile  userProfile;
    ArrayList<EmergencyContact> emergencyContacts;
    ArrayList<Vehicle> vehicle;

    public User() {

    }

    public User(UserProfile userProfile, ArrayList<EmergencyContact> emergencyContacts, ArrayList<Vehicle> vehicle) {
        this.userProfile = userProfile;
        this.emergencyContacts = emergencyContacts;
        this.vehicle = vehicle;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public ArrayList<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(ArrayList<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public ArrayList<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(ArrayList<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }
}
