package Grades;

import OldestPerson.Person;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GradeSystem {

    List<Student> students = new ArrayList<>();

    public void loadData(InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        students = bufferedReader.lines()
                .map(line -> new Student(line))
                .collect(Collectors.toList());
    }

    public void printResults(OutputStream outputStream){

        PrintWriter printWriter = new PrintWriter(outputStream);

        students.stream()
                .sorted()
                .forEach(student -> printWriter.println(student));


        printWriter.flush();
    }

}
