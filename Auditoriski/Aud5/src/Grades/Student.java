package Grades;

public class Student implements Comparable<Student>{

    private String first_name;
    private String last_name;
    private int exam1;
    private int exam2;
    private int exam3;

    public Student(String first_name, String last_name, int exam1, int exam2, int exam3) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
    }

    public Student(String line){

        String[] parts = line.split(":");

        this.last_name = parts[0];
        this.first_name = parts[1];
        this.exam1 = Integer.parseInt(parts[2]);
        this.exam2 = Integer.parseInt(parts[3]);
        this.exam3 = Integer.parseInt(parts[4]);

    }

    public double totalPoints(){

        return exam1 * 0.25 + exam2 * 0.30 + exam3 * 0.45;
    }

    public String grade(){

        double pts = totalPoints();

        if(pts >= 90){
            return "A";
        }
        else if(pts >= 80) {
            return "B";
        }
        else if(pts >= 70){
            return "C";
        }
        else if(pts >= 60){
            return "D";
        }
        else {
            return "F";
        }
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(totalPoints(),o.totalPoints());
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %.2f %s", last_name,first_name,exam1,exam2,exam3, totalPoints(), grade());
    }
}
