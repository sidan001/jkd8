package com.chou.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CollectorTest {
    public static void main(String[] args) {
        Student student1 = new Student("derek",30, Student.SEX.MALE,90,false);
        Student student2 = new Student("karev",25, Student.SEX.MALE,70,false);
        Student student3 = new Student("owen",30, Student.SEX.MALE,80,true);

        Student student4 = new Student("izze",25, Student.SEX.FEMALE,80,false);
        Student student5 = new Student("yang",27, Student.SEX.FEMALE,95,true);
        Student student6 = new Student("mere",28, Student.SEX.FEMALE,90,false);
        Student student7 = new Student("geroge",29, Student.SEX.MALE,90,false);
        Student student8 = new Student("lexsi",23, Student.SEX.FEMALE,95,true);


        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6,student7,student8);

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

        //按性别分组，求每组的总分
        final Map<Student.SEX, Integer> sexTotalScoreMap = students.stream().collect(
                Collectors.groupingBy(Student::getSex,
                        Collectors.summingInt(Student::getScore)));
        System.out.println(sexTotalScoreMap);
        //按性别分组，求每组的平均分
        final Map<Student.SEX, Double> sexAveScoreMap = students.stream().collect(
                Collectors.groupingBy(Student::getSex,
                        Collectors.averagingInt(Student::getScore)));
        System.out.println(sexAveScoreMap);

        // 按分数分区 ，再按性别分组并求每组的总分数
        final Map<Boolean, Map<Student.SEX, Integer>> collect = students.stream().collect(
                Collectors.partitioningBy(
                        s -> s.getScore() > 80,
                        Collectors.groupingBy(Student::getSex,
                                Collectors.summingInt(Student::getScore))));
        System.out.println(collect);


        //按性别分组，取每组分最高的student，分数相同的取年龄最大的
        final Map<Student.SEX, Optional<Student>> collect1 = students.stream().collect(
                Collectors.groupingBy(
                        Student::getSex,
                        Collectors.maxBy(Comparator.comparing(Student::getScore).thenComparingInt(Student::getAge))));
        System.out.println(collect1);

        final Map<Student.SEX, Student> collect1_1 = students.stream().collect(
                Collectors.groupingBy(
                        Student::getSex,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Student::getScore).thenComparingInt(Student::getAge)),
                                Optional::get)));





        //按性别分组，取每组最高的分数
        final Map<Student.SEX, Integer> collect2 = students.stream().collect(
                Collectors.groupingBy(
                        Student::getSex,
                        Collectors.reducing(0, a -> a.getScore(), (a, b) -> a > b ? a : b)));
        System.out.println(collect2);

        //按性别分组，统计每个组分数的概览信息
        final Map<Student.SEX, IntSummaryStatistics> collect3 = students.stream().collect(
                Collectors.groupingBy(
                        Student::getSex,
                        Collectors.summarizingInt(Student::getScore)));

        System.out.println(collect3);


        // 统计学生的总分数
        final Integer totoalScoreByCollect = students.stream().collect(Collectors.reducing(0, Student::getScore, Integer::sum));

        final Integer totoalScoreByReduce = students.stream().reduce(0, (a, b) -> a + b.getScore(), Integer::sum);

        final Integer totaolScoreByMapReduce = students.stream().map(Student::getScore).reduce(Integer::sum).get();

        final int totaolScoreByMapToInt = students.stream().mapToInt(Student::getScore).sum();

        //素食主义者
        final List<String> vegetarianStudents = students.stream()
                .filter(Student::isVegetarian)
                .collect(Collectors.mapping(Student::getName, Collectors.toList()));
        System.out.println(vegetarianStudents);





    }
}
