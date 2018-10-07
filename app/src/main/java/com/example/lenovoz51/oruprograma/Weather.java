package com.example.lenovoz51.oruprograma;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class Weather {
    public Location location;
    public String iconData;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
