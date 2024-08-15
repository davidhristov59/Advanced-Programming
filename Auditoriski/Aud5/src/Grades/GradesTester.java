package Grades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GradesTester{

    public static void main(String[] args) {

        try {
            InputStream inputStream = new FileInputStream(new File("/Users/davidhristov/Desktop/FINKI /Napredno Programiranje/Auditoriski/Aud5/src/Grades/students.txt"));
            GradeSystem gradeSystem = new GradeSystem();
            gradeSystem.loadData(inputStream);
            gradeSystem.printResults(System.out);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}