package com.ppqppl.stm32_server.entity;

import javax.persistence.*;

@Entity
@Table(name="tcp")
public class TCP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int num;
    public String strain;
    public String temp;
    public String time;

    public TCP() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
