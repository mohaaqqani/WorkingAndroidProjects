package com.example.omer.databasetry2;

/**
 * Created by OMER on 7/24/2017.
 */

public class Contact {

    private int _id;
    private String  _name;
    private String _phone_number;

    public Contact(){}

    public Contact(int  id,String name,String   phone_number){
        this._id    =   id;
        this._name  =   name;
        this._phone_number  =   phone_number;
    }

    public Contact(String   name,String phone_number){
        this._name  =   name;
        this._phone_number  =   phone_number;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getPhoneNumber() {
        return this._phone_number;
    }

    public void setPhoneNumber(String   phoneNumber){
        this._phone_number  =   phoneNumber;
    }
}
