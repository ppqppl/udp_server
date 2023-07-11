package com.ppqppl.stm32_server.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public String account;
    public String pwd;
    public String mail;

    public User() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
