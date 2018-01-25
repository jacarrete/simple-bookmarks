package com.simple.bookmarks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by jcarretero on 24/01/2018.
 */
@Entity
@Table(name="BOOKMARK")
public class Bookmark {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @NotNull
    String name;

    @Column(name="initials")
    @NotNull
    String initials;

    @Column(name="address")
    @NotNull
    String address;

    @Column(name="color")
    @NotNull
    String color;

    @Column(name="imageName")
    String imageName;

    @Column(name="showText")
    Boolean showText;

    public Bookmark() {
        super();
    }

    public Bookmark(String name, String initials, String address, String color, String imageName, Boolean showText) {
        this.name = name;
        this.initials = initials;
        this.address = address;
        this.color = color;
        this.imageName = imageName;
        this.showText = showText;
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

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Boolean getShowText() {
        return showText;
    }

    public void setShowText(Boolean showText) {
        this.showText = showText;
    }
}
