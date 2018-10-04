/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.area52.techno.models;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DJ {

    // Required
    public String id;
    public String name;
    public String image;
    public String djLogoImage;
    public String county;
    public String country;
    public String email;
    public String phoneNumber;

    // Optional
    public String facebookUserID;
    public String bio;
    public String soundcloudLink;
    public String youtubeLink;
    public String mixcloudLink;
    public String facebookLink;
    public String instagramLink;
    public String spotifyLink;
    public String bookingEmail;
    public String genre;

    public DJ(String id, String name, String image, String djLogoImage, String county, String country, String email, String phoneNumber, String facebookUserID, String bio, String soundcloudLink, String youtubeLink, String mixcloudLink, String facebookLink, String instagramLink, String spotifyLink, String bookingEmail, String genre) {

        this.name = name;
        this.image = image;
        this.djLogoImage = djLogoImage;
        this.county = county;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebookUserID = facebookUserID;
        this.bio = bio;
        this.soundcloudLink = soundcloudLink;
        this.youtubeLink = youtubeLink;
        this.mixcloudLink = mixcloudLink;
        this.facebookLink = facebookLink;
        this.instagramLink = instagramLink;
        this.spotifyLink = spotifyLink;
        this.bookingEmail = bookingEmail;
        this.genre = genre;
    }

    public DJ(String email, String name) {

     this.email = email;
     this.name = name;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDjLogoImage() {
        return djLogoImage;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFacebookUserID() {
        return facebookUserID;
    }

    public String getBio() {
        return bio;
    }

    public String getSoundcloudLink() {
        return soundcloudLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public String getMixcloudLink() {
        return mixcloudLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public String getSpotifyLink() {
        return spotifyLink;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDjLogoImage(String djLogoImage) {
        this.djLogoImage = djLogoImage;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFacebookUserID(String facebookUserID) {
        this.facebookUserID = facebookUserID;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setSoundcloudLink(String soundcloudLink) {
        this.soundcloudLink = soundcloudLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public void setMixcloudLink(String mixcloudLink) {
        this.mixcloudLink = mixcloudLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public void setSpotifyLink(String spotifyLink) {
        this.spotifyLink = spotifyLink;
    }

    public void setBookingEmail(String bookingEmail) {
        this.bookingEmail = bookingEmail;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("name", this.name);
        result.put("bio", this.bio);

        return result;
    }
}
