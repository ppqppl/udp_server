package com.ppqppl.stm32_server.entity;

import javax.persistence.*;

@Entity
@Table(name="udp_temp_humi")
public class Udp_temp_humi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String temp;
    public String humi;
    public String time;

    public Udp_temp_humi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
