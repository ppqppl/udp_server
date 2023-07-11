package com.ppqppl.stm32_server.entity;

import javax.persistence.*;

@Entity
@Table(name="udp")
public class UDP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int num;
    public String deflect;
    public String time;

    public UDP() {
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

    public String getDeflect() {
        return deflect;
    }

    public void setDeflect(String deflect) {
        this.deflect = deflect;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
