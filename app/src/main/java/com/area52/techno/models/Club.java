package com.area52.techno.models;

import android.location.Location;

public class Club {

    String uID;
    String facebookLink;
    String name;
    String photoUrl;
    double latitiude;
    double longitude;
    double miles;

    public Club(String uID, String facebookLink, String name, String photoUrl, double latitiude, double longitude, double miles) {
        this.facebookLink = facebookLink;
        this.uID = uID;
        this.name = name;
        this.photoUrl = photoUrl;
        this.latitiude = latitiude;
        this.longitude = longitude;
        this.miles = miles;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public Club() {

    }

    public double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(double latitiude) {
        this.latitiude = latitiude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String id) {
        this.uID = id;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + uID + '\'' +
                ", fbID='" + facebookLink + '\'' +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
