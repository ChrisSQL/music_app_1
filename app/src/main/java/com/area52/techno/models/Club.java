package com.area52.techno.models;

import android.location.Location;

public class Club {

    String uID;
    String facebookLink;
    String name;
    String photoUrl;
    Location location;

    public Club(String uID, String facebookLink, String name, String photoUrl, Location location) {
        this.facebookLink = facebookLink;
        this.uID = uID;
        this.name = name;
        this.photoUrl = photoUrl;
        this.location = location;
    }

    public Club() {

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
