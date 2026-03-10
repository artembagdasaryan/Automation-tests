package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

 class Student {
     private String name;
     private int course;

     public Student(String name, int course) {
         this.name = name;
         this.course = course;
     }

     public String getName() {
         return name;
     }

     public int getCourse() {
         return course;
     }

     @Override
     public String toString() {
         return "Student{" +
                 "name='" + name + '\'' +
                 ", course=" + course +
                 '}';
     }

     public static void printStudents(List<Student> students, int courseNumber) {
         Iterator<Student> iterator = students.iterator();
         while (iterator.hasNext()) {
             Student student = iterator.next();
             if (student.getCourse() == courseNumber) {
                 System.out.println(student.getName());
             }
         }
     }

     public static Comparator<Student> compareByName() {
         return Comparator.comparing(Student::getName);
     }

     public static Comparator<Student> compareByCourse() {
         return Comparator.comparing(Student::getCourse);
     }

     public static void main(String[] args) {
         List<Student> students = new ArrayList<>();
         students.add(new Student("John Doe", 1));
         students.add(new Student("Jane Smith", 2));
         students.add(new Student("Alice Johnson", 3));
         students.add(new Student("Bob Brown", 4));
         students.add(new Student("Charlie Davis", 5));
         students.sort(compareByName());
            for (Student student : students) {
                System.out.println(student);
            }
         printStudents(students, 2);

         students.sort(compareByCourse());
         for (Student student : students) {
             System.out.println(student);
         }
            printStudents(students, 3);
     }
 }

