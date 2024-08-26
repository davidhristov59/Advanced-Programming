package z6_PayrollSystem;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

abstract class Employee{

    public String ID;
    public String level;
    public static Comparator<Employee> EMPLOYEE_COMPARATOR =
            Comparator.comparing(Employee::salary)
                    .reversed()
                    .thenComparing(Employee::getLevel);

    public Employee(String ID, String level) {
        this.ID = ID;
        this.level = level;
    }

    public abstract double salary();

    public String getID() {
        return ID;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", ID, level, salary());
    }
}

class HourlyEmployee extends Employee{

    public double hours;
    public double hourly_rate_by_level;
    public double overtime_hours;
    public double regular_hours;

    public HourlyEmployee(String ID, String level, double hours, double hourly_rate_by_level) {
        super(ID, level);
        this.hours = hours;
        this.hourly_rate_by_level = hourly_rate_by_level;

        if(hours <= 40){
            regular_hours = hours;
            overtime_hours = 0;
        }
        else { //>40, overtime
            regular_hours = 40;
            overtime_hours = hours - 40;
        }
    }

    @Override
    public double salary() {
        return (regular_hours * hourly_rate_by_level + overtime_hours * hourly_rate_by_level * 1.500);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Regular hours: %.2f Overtime hours: %.2f", regular_hours, overtime_hours);
    }
}

class FreelanceEmployee extends Employee{

    public List<Integer> ticket_points;
    public double ticket_rate_by_level;

    public FreelanceEmployee(String ID, String level, double ticket_rate_by_level, List<Integer> ticket_points) {
        super(ID, level);
        this.ticket_points = ticket_points;
        this.ticket_rate_by_level = ticket_rate_by_level;
    }

    public int sum_tickets(){
        return ticket_points.stream().mapToInt(i -> i).sum();
    }

    @Override
    public double salary() {
        return (double) sum_tickets() * ticket_rate_by_level;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Tickets count: %d Tickets points: %d", ticket_points.size(), sum_tickets());
    }
}

class PayrollSystem{

    Map<String, Double> hourlyEmployees;
    Map<String, Double> freelanceEmployees;
    List<Employee> employees;

    public PayrollSystem(Map<String, Double> hourlyEmployees, Map<String, Double> freelanceEmployees) {
        this.hourlyEmployees = hourlyEmployees;
        this.freelanceEmployees = freelanceEmployees;
        employees = new ArrayList<>();
    }

    public void readEmployeesData (InputStream is){

        // HourlyEmployee: H;ID;level;hours;
        //FreelanceEmployee: F;ID;level;ticketPoints1;ticketPoints2;...;ticketPointsN
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        List<String> inputs = bufferedReader.lines().collect(Collectors.toList());

        for(String line : inputs){

            String[] parts = line.split(";");

            String type_of_employee = parts[0];
            String ID = parts[1];
            String level = parts[2];

            if(type_of_employee.equals("H")){

                double hours = Double.parseDouble(parts[3]);

                HourlyEmployee hourlyEmployee = new HourlyEmployee(ID,level, hours, hourlyEmployees.get(level));
                employees.add(hourlyEmployee);
            }
            else { //F - Freelance Employee

                List<Integer> ticket_points = Arrays.stream(parts)
                        .skip(3)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                FreelanceEmployee freelanceEmployee = new FreelanceEmployee(ID, level, freelanceEmployees.get(level), ticket_points);
                employees.add(freelanceEmployee);
            }
        }
    }

    Map<String, Set<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels){

        PrintWriter printWriter = new PrintWriter(os);

        Map<String, Set<Employee>> level_employees = new TreeMap<>();

        levels.forEach(level -> {
            employees.stream()
                    .filter(l -> l.getLevel().equals(level))
                    .forEach(employee -> {
                        level_employees.putIfAbsent(level, new TreeSet<>(Employee.EMPLOYEE_COMPARATOR));
                        level_employees.get(level).add(employee);
                    });
        });

        printWriter.flush();

        return level_employees;
    }

}


public class PayrollSystemTest {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
        }

        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);

        System.out.println("READING OF THE EMPLOYEES DATA");
        payrollSystem.readEmployeesData(System.in);

        System.out.println("PRINTING EMPLOYEES BY LEVEL");
        Set<String> levels = new LinkedHashSet<>();
        for (int i=5;i<=10;i++) {
            levels.add("level"+i);
        }
        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
        result.forEach((level, employees) -> {
            System.out.println("LEVEL: "+ level);
            System.out.println("Employees: ");
            employees.forEach(System.out::println);
            System.out.println("------------");
        });


    }
}