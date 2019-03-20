package com.stationsfinder.administrator.stationsfinder.datamodel;

/**
 * Created by Administrator on 12/12/2017.
 */

public class emergency {
    private int id;
    private String name;
    private  String phoneno;
    private  String address;
    private String latitute;
    private  String longtitute;
    private  String township;

    public emergency(String name, String phoneno, String address, String latitute, String longtitute, String township) {
        this.name = name;
        this.phoneno = phoneno;
        this.address = address;
        this.latitute = latitute;
        this.longtitute = longtitute;
        this.township = township;
    }

    public emergency(int id, String name, String phoneno, String address, String latitute, String longtitute, String township) {
        this.id = id;
        this.name = name;
        this.phoneno = phoneno;
        this.address = address;
        this.latitute = latitute;
        this.longtitute = longtitute;
        this.township = township;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLongtitute() {
        return longtitute;
    }

    public void setLongtitute(String longtitute) {
        this.longtitute = longtitute;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }
}
