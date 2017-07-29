package com.example.suneet.onroadwheels;

/**
 * Created by suneet on 29/7/17.
 */

class EmergencyContact {
    private String mobileNo;
    private String relation;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public EmergencyContact(String mobileNo, String relation) {

        this.mobileNo = mobileNo;
        this.relation = relation;
    }
}
