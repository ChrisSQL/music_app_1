package com.area52.techno.models;

import android.net.Uri;

import java.net.URI;

public class User {

    String uID;
    String fbID;
    String name;
    String email;
    String photoUrl;
    String refDJ;

    public User(String uID, String fbID, String name, String email, String photoUrl, String refDJ) {
        this.fbID = fbID;
        this.uID = uID;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.refDJ = refDJ;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefDJ() {
        return refDJ;
    }

    public void setRefDJ(String refDJ) {
        this.refDJ = refDJ;
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

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + uID + '\'' +
                ", fbID='" + fbID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
