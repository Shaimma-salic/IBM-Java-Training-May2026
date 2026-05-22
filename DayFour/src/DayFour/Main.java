package DayFour;
import java.util.*;

public class Main {
    public static void main (String[] args) {
        
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 50000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 50000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name

        // Using a set to filter out duplicates based on employee name
        Set<String> uniqueNames = new HashSet<>();
        List<Employee> uniqueEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (uniqueNames.add(emp.getName())) { // indicator
                uniqueEmployees.add(emp);
            }
        }

        // Print unique employees      
        System.out.println();  
        System.out.println("=== Unique Employees ===");
        for (Employee emp : uniqueEmployees) {
            System.out.println(emp);        
    }

    // Grouping employees by departmen t with key and value a list of employees in that department
    Map<String, List<Employee>> departmentGroups = new HashMap<>();
    for (Employee emp : employees) {
        departmentGroups.computeIfAbsent(emp.getDepartment(), k -> new ArrayList<>()).add(emp);
    }

    // Print employees grouped by department
    System.out.println();  
    System.out.println("=== Employees Grouped by Department ===");
    for (Map.Entry<String, List<Employee>> entry : departmentGroups.entrySet()) {
        System.out.println(entry.getKey() + ":");
        for (Employee emp : entry.getValue()) {
            //System.out.println(" - " + emp.getName() + " (" + emp.getSalary() + ")");
            System.out.println(" - " + emp);     
        }
    }

    //highest paid per department
    System.out.println();
    System.out.println("=== Highest Paid Employee per Department ===");
    for (Map.Entry<String, List<Employee>> entry : departmentGroups.entrySet()) {
        String department = entry.getKey();
        List<Employee> deptEmployees = entry.getValue();    
        Employee highestPaid = Collections.max(deptEmployees, Comparator.comparingDouble(Employee::getSalary));
        System.out.println(department + ": " + highestPaid);
    }

    // Print employees grouped by Salary (Desc)
    System.out.println();
    System.out.println("=== Employees Grouped by Salary (Desc) ===");
    Collections.sort(employees, (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
    for (Employee emp : employees) {
        System.out.println(emp);
    }
    
    System.out.println();
    System.out.println("=== Unique Salaries ===");
    Set<Double> uniqueSalaries = new HashSet<>();
    for (Employee emp : employees) {
        uniqueSalaries.add(emp.getSalary());
    }
    List<Double> sortedSalaries = new ArrayList<>(uniqueSalaries);
    Collections.sort(sortedSalaries);
    for (Double salary : sortedSalaries) {
        System.out.println("$" + salary);
    }

}
}
