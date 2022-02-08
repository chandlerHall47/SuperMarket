package com.example.supermarket;

import java.io.Serializable;


public class SuperMarket implements Serializable {
    private int marketID;
    private String superMarketName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String rating;


    public SuperMarket() {
        marketID = -1;

    }

    public int getMarketID() {
        return marketID;
    }

    public void setMarketID(int i){
        marketID = i ;
    }
    public String getName(){
        return superMarketName;
    }

    public void setName(String s){
        superMarketName = s;
    }
    public String getStreetAddress(){
        return streetAddress;
    }
    public void setStreetAddress(String s){
        streetAddress = s;

    }

    public String getCity() {
        return  city;
    }

    public void setCity(String s){
        city = s;
    }

    public String getState(){
        return state;
    }

    public void setState(String s){
        state = s;
    }
    public String getZipCode(){
        return  zipCode;
    }
    public void setZipCode(String s){
        zipCode = s;
    }

    public String getRating(){
    return rating;
    }
    public void setRating(String s){
        rating = s;
    }



}
