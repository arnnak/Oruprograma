package com.example.lenovoz51.oruprograma;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class Location {
    private float lon;
    private float lan;
    private long sunset;
    private long sunrise;
    private String country;
    private String city;
    private long lastUpdate;

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public float getLan() {
        return lan;
    }

    public void setLan(float lan) {
        this.lan = lan;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
