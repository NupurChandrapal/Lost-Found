package com.sazal.siddiqui.lost_people.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sazal on 2017-10-15.
 */

public class LostFoundData implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("bodyColor")
    @Expose
    private String bodyColor;
    @SerializedName("bodyMark")
    @Expose
    private String bodyMark;
    @SerializedName("dress")
    @Expose
    private String dress;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("foundContact")
    @Expose
    private String foundContact;
    @SerializedName("foundAddress")
    @Expose
    private String foundAddress;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("lostPlace")
    @Expose
    private String lostPlace;
    @SerializedName("prize")
    @Expose
    private String prize;
    @SerializedName("isLost")
    @Expose
    private short isLost;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;

    public LostFoundData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public String getBodyMark() {
        return bodyMark;
    }

    public void setBodyMark(String bodyMark) {
        this.bodyMark = bodyMark;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFoundContact() {
        return foundContact;
    }

    public void setFoundContact(String foundContact) {
        this.foundContact = foundContact;
    }

    public String getFoundAddress() {
        return foundAddress;
    }

    public void setFoundAddress(String foundAddress) {
        this.foundAddress = foundAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public void setLostPlace(String lostPlace) {
        this.lostPlace = lostPlace;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public short getIsLost() {
        return isLost;
    }

    public void setIsLost(short isLost) {
        this.isLost = isLost;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}
