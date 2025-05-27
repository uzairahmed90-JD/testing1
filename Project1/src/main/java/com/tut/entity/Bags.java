package com.tut.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Date;
@Entity
public class Bags {
    @Id@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long  shift1;
    private long  shift2;
    private long  shift3;
    private long  shift4;
    private LocalDate date;


    private long perdaybag;
    private long totalBags;

    private long minus;
    private long remain;

    public long getRemain() {
        return remain;
    }

    public void setRemain(long remain) {
        this.remain=remain;
    }

    public long getMinus() {
        return minus;
    }

    public void setMinus(long minus) {
        this.minus=minus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public long getShift1() {
        return shift1;
    }

    public void setShift1(long shift1) {
        this.shift1=shift1;
    }

    public long getShift2() {
        return shift2;
    }

    public void setShift2(long shift2) {
        this.shift2=shift2;
    }

    public long getShift3() {
        return shift3;
    }

    public void setShift3(long shift3) {
        this.shift3=shift3;
    }

    public long getShift4() {
        return shift4;
    }

    public void setShift4(long shift4) {
        this.shift4=shift4;
    }


    public long getPerdaybag() {
        return perdaybag;
    }

    public void setPerdaybag(long perdaybag) {
        this.perdaybag=perdaybag;
    }

    public long getTotalBags() {
        return totalBags;
    }

    public void setTotalBags(long totalBags) {
        this.totalBags=totalBags;
    }
}
