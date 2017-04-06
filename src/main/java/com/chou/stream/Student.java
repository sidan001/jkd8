package com.chou.stream;


public class Student {
    String name;
    int age;
    SEX sex;
    int score;
    boolean isVegetarian;

    public Student(String name, int age, SEX sex, int score) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.score = score;
    }

    public Student(String name, int age, SEX sex, int score, boolean isVegetarian) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.score = score;
        this.isVegetarian = isVegetarian;
    }

    enum SEX{
        MALE,FEMALE;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", score=" + score +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
