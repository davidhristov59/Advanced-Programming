package z26_StudentRecords;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Student{

    String ID;
    String major;
    List<Integer> grades;

    public static Comparator<Student> STUDENT_COMPARATOR =
            Comparator.comparing(Student::averageGrade)
                    .reversed()
                    .thenComparing(Student::getID);

    public Student(String ID, String major, List<Integer> grades) {
        this.ID = ID;
        this.major = major;
        this.grades = grades;
    }

    public static Student createStudent(String line){

        //ioqmx7 MT 10 8 10 8 10 7 6 9 9 9 6 8 6 6 9 9 8

        String[] parts = line.split("\\s+");

        String ID = parts[0];
        String major = parts[1];
        List<Integer> grades =
                Arrays.stream(parts)
                        .skip(2)
                        .map(Integer::parseInt) //site parsiraj gi vo brojki
                        .collect(Collectors.toList());


        return new Student(ID, major, grades);
    }

    public double averageGrade(){ //prosek
        return grades.stream()
                .mapToDouble(i -> i)
                .summaryStatistics()
                .getAverage();
    }

    public double averageGrade2(){
        return (double) grades.stream()
                .mapToInt(i -> i)
                .sum() / grades.size();
    }

    public String getID() {
        return ID;
    }

    public String getMajor() {
        return major;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", ID, averageGrade());
    }
}

class StudentRecords{

    public Map<String, Set<Student>> students;

    public StudentRecords(){
        students = new TreeMap<>();
    }

    public int readRecords(InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> inputs = bufferedReader.lines().collect(Collectors.toList());

        int counter = 0;
        for(String input : inputs){

            Student newStudent = Student.createStudent(input);

            students.putIfAbsent(newStudent.getMajor(), new TreeSet<>(Student.STUDENT_COMPARATOR));
            students.get(newStudent.getMajor()).add(newStudent);
            counter++;
        }

        return counter;
    }

    public void writeTable(OutputStream outputStream){

        PrintWriter printWriter = new PrintWriter(outputStream);

        students.keySet()
                .stream()
                .forEach(student -> {
                    printWriter.println(student);
                    students.get(student)
                            .stream()
                            .sorted(Student.STUDENT_COMPARATOR)
                            .forEach(i -> printWriter.println(i));
                });

        printWriter.flush();
    }

    public void writeDistribution(OutputStream outputStream){ //ne go znam metodov - izgeneriran e
        PrintWriter pw = new PrintWriter(outputStream);
        Map<String,Map<Integer,Integer>> mapMap = new HashMap<>();
        students.forEach((key, value) -> {
            mapMap.putIfAbsent(key, new HashMap<>());
            for (int i = 6; i <= 10; i++) {
                mapMap.get(key).put(i, 0);
            }
            students.get(key).stream()
                    .map(Student::getGrades)
                    .flatMap(Collection::stream)
                    .forEach(grade -> mapMap.get(key).computeIfPresent(grade, (k, v) -> ++v));
        });
        mapMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(innerMap->-innerMap.get(10))))
                .map(Map.Entry::getKey)
                .forEach(key->{
                    pw.println(key);
                    mapMap.get(key).keySet().stream()
                            .forEach(innerKey->{
                                StringBuilder stars = new StringBuilder();
                                int numOfInnerKey = mapMap.get(key).get(innerKey);
                                IntStream.range(0, (int) Math.ceil((double) numOfInnerKey /10)).mapToObj(i->"*").forEach(stars::append);
                                pw.println(String.format("%2d | %s(%d)",innerKey,stars,numOfInnerKey));
                            });
                });

        pw.flush();
    }

}

public class StudentRecordsTest {
    public static void main(String[] args) {
        System.out.println("=== READING RECORDS ===");
        StudentRecords studentRecords = new StudentRecords();
        int total = studentRecords.readRecords(System.in);
        System.out.printf("Total records: %d\n", total);
        System.out.println("=== WRITING TABLE ===");
        studentRecords.writeTable(System.out);
        System.out.println("=== WRITING DISTRIBUTION ===");
        studentRecords.writeDistribution(System.out);
    }
}