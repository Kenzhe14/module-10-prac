import java.util.ArrayList;
import java.util.List;

abstract class OrganizationComponent {
    protected String name;
    protected double salary;

    public OrganizationComponent(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public abstract void add(OrganizationComponent component);
    public abstract void display(int depth);
}

class Employee extends OrganizationComponent {

    public Employee(String name, double salary) {
        super(name, salary);
    }

    @Override
    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot add to an employee.");
    }

    @Override
    public void display(int depth) {
        System.out.println("  ".repeat(depth) + "Employee: " + name + ", Salary: " + salary);
    }
}

class Department extends OrganizationComponent {
    private List<OrganizationComponent> components;

    public Department(String name) {
        super(name, 0);
        components = new ArrayList<>();
    }

    @Override
    public void add(OrganizationComponent component) {
        components.add(component);
    }

    @Override
    public void display(int depth) {
        System.out.println("  ".repeat(depth) + "Department: " + name);
        for (OrganizationComponent component : components) {
            component.display(depth + 1);
        }
    }
}

public class Main2 {
    public static void main(String[] args) {
        Department headOffice = new Department("Head Office");
        Department salesDepartment = new Department("Sales Department");
        Department devDepartment = new Department("Development Department");

        Employee emp1 = new Employee("Erko", 60000);
        Employee emp2 = new Employee("Manat", 55000);
        Employee emp3 = new Employee("Beka", 70000);

        salesDepartment.add(emp1);
        salesDepartment.add(emp2);
        devDepartment.add(emp3);

        headOffice.add(salesDepartment);
        headOffice.add(devDepartment);

        headOffice.display(0);
    }
}