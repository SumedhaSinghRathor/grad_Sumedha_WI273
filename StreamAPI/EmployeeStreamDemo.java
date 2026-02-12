import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;

class Employee {
    String name;
    int age;
    String gender;
    double salary;
    String designation;
    String department;

    public Employee(String name, int age, String gender, double salary, String designation, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getSalary() { return salary; }
    public String getDesignation() { return designation; }
    public String getDepartment() { return department; }

    public void setSalary(double salary) { this.salary = salary; }

    public String toString() {
        return name + " | " + designation + " | " + salary;
    }
}

public class EmployeeStreamDemo {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Amit", 45, "Male", 90000, "Manager", "IT"),
                new Employee("Neha", 30, "Female", 60000, "Developer", "IT"),
                new Employee("Rahul", 50, "Male", 120000, "Director", "HR"),
                new Employee("Priya", 28, "Female", 50000, "Developer", "HR"),
                new Employee("Vikas", 38, "Male", 75000, "Manager", "Sales"),
                new Employee("Sneha", 26, "Female", 45000, "Executive", "Sales"),
                new Employee("Arjun", 55, "Male", 150000, "CEO", "Management")
        );

        Optional<Employee> highestSalary = employees.stream().max(Comparator.comparing(Employee::getSalary));

        highestSalary.ifPresent(e -> System.out.println("Highest Salary: " + e));

        Map<String, Long> genderCount = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        System.out.println("Gender Count: " + genderCount);

        Map<String, Double> deptExpense = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));

        System.out.println("Department Wise Expense: " + deptExpense);

        List<Employee> top5Senior = employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).limit(5).collect(Collectors.toList());

        System.out.println("Top 5 Senior Employees:");
        top5Senior.forEach(System.out::println);

        List<String> managers = employees.stream().filter(e -> e.getDesignation().equalsIgnoreCase("Manager")).map(Employee::getName).collect(Collectors.toList());

        System.out.println("Managers: " + managers);

        employees.stream().filter(e -> !e.getDesignation().equalsIgnoreCase("Manager")).forEach(e -> e.setSalary(e.getSalary() * 1.20));

        System.out.println("After Salary Hike:");
        employees.forEach(System.out::println);

        long totalEmployees = employees.stream().count();

        System.out.println("Total Employees: " + totalEmployees);
    }
}

