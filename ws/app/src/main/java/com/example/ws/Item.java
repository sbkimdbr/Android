package com.example.ws;

public class Item {

    String id;
    String name;
    String age;
    String img;

    public Item() {
    }

    public Item(String id, String name, String age, String img) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.img = img;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

