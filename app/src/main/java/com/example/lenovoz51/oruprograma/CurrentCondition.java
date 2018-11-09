package com.example.lenovoz51.oruprograma;

import java.io.Serializable;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class CurrentCondition implements Serializable {
    private int weatherId;
    private String condition;
    private String description;
    private String icon;
    private float preasure;
    private float humidity;
    private float maxTemp;
    private float minTemp;
    private double temp;

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getPreasure() {
        return preasure;
    }

    public void setPreasure(float preasure) {
        this.preasure = preasure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
