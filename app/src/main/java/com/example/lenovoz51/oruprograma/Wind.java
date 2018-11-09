package com.example.lenovoz51.oruprograma;

import java.io.Serializable;

/**
 * Created by Lenovo Z51 on 2018-10-07.
 */

public class Wind implements Serializable {
    private float speed;
    private float deg;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDeg() {
        return deg;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }
}
