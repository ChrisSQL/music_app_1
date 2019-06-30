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

import java.util.HashMap;
import java.util.Map;

public class Festival {

    // Required
    public String uID;
    public String name;
    public String PhotoUrl;
    public String county;
    public String country;

    // Optional
    public String soundcloudLink;
    public String youtubeLink;
    public String youtubePlaylistLink;
    public String mixcloudLink;
    public String facebookLink;
    public String instagramLink;
    public String spotifyLink;
    public String genre;
    public String start_date_timestamp;
    public String ticketsLink;

    public Festival(String uID, String name, String PhotoUrl,  String county, String country, String soundcloudLink, String youtubeLink, String youtubePlaylistLink, String mixcloudLink, String facebookLink, String instagramLink, String spotifyLink, String genre, String start_date_timestamp, String ticketsLink) {

        this.uID = uID;
        this.name = name;
        this.PhotoUrl = PhotoUrl;
        this.county = county;
        this.country = country;
        this.soundcloudLink = soundcloudLink;
        this.youtubeLink = youtubeLink;
        this.youtubePlaylistLink = youtubePlaylistLink;
        this.mixcloudLink = mixcloudLink;
        this.facebookLink = facebookLink;
        this.instagramLink = instagramLink;
        this.spotifyLink = spotifyLink;
        this.genre = genre;
        this.start_date_timestamp = start_date_timestamp;
        this.ticketsLink = ticketsLink;
    }

    public Festival() {

    }

    public String getYoutubePlaylistLink() {
        return youtubePlaylistLink;
    }

    public void setYoutubePlaylistLink(String youtubePlaylistLink) {
        this.youtubePlaylistLink = youtubePlaylistLink;
    }

    public String getTicketsLink() {
        return ticketsLink;
    }

    public void setTicketsLink(String ticketsLink) {
        this.ticketsLink = ticketsLink;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrlDJ() {
        return PhotoUrl;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
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

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photoUrl) {
        this.PhotoUrl = photoUrl;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStart_date_timestamp() {
        return start_date_timestamp;
    }

    public void setStart_date_timestamp(String start_date_timestamp) {
        this.start_date_timestamp = start_date_timestamp;
    }
}
