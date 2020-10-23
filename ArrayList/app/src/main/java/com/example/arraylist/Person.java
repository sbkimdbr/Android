package com.example.arraylist;

public class Person {

    int img;
    String id;
    String name;
    String age;

    public Person(int m9, String cee, String name, int img) {
        this.img = img;
    }

    public Person(int img, String id, String name, String age) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
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
}
