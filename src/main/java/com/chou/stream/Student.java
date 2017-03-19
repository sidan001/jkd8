package com.chou.stream;


public class Student {
    String name;
    int age;
    SEX sex;
    int score;

    public Student(String name, int age, SEX sex, int score) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.score = score;
    }

    enum SEX{
        MALE,FEMALE;
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
                '}';
    }
}
