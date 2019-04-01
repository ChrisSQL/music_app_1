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

public class Set {

    public final String artistuID;
    public final String artistName;
    public final String title;
    public final String genre;
    public final String uID;
    public final String url;


    public Set() {
        this.genre = "";
        this.uID = "";
        this.artistuID = "";
        this.title = "";
        this.artistName = "";
        this.url = "";
    }

    public Set(String artistuID, String artistName, String title, String genre, String uID, String url) {
        this.artistuID = artistuID;
        this.artistName = artistName;
        this.title = title;
        this.genre = genre;
        this.uID = uID;
        this.url = url;
    }

    public String getArtistuID() {
        return artistuID;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getuID() {
        return uID;
    }

    public String getUrl() {
        return url;
    }
}

