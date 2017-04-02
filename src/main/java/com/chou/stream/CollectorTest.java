package com.chou.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CollectorTest {
    public static void main(String[] args) {
        Student student1 = new Student("derek",30, Student.SEX.MALE,90);
        Student student2 = new Student("karev",25, Student.SEX.MALE,70);
        Student student3 = new Student("owen",30, Student.SEX.MALE,80);
        Student student4 = new Student("izze",25, Student.SEX.FEMALE,80);
        Student student5 = new Student("yang",27, Student.SEX.FEMALE,95);
        Student student6 = new Student("mere",28, Student.SEX.FEMALE,90);

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6);

        //按年龄分组
        Map<Integer, List<Student>> groupByAge = students.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(groupByAge);

        // 按分数分区 ，再按性别分组
        Map<Boolean, Map<Student.SEX, List<Student>>> booleanMap = students.stream().collect(
                Collectors.partitioningBy(s -> s.getScore() > 80,
                        Collectors.groupingBy(Student::getSex)));
        System.out.println(booleanMap);

        //按性别分组再按年龄分组
        Map<Student.SEX, Map<Integer, List<Student>>> sexMapMap = students.stream().collect(
                Collectors.groupingBy(Student::getSex,
                        Collectors.groupingBy(Student::getAge)));
        System.out.println(sexMapMap);

    }
}
