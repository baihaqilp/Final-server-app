package id.co.metrodata.serverApp.services;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Employee;
import id.co.metrodata.serverApp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private RoleService roleService;

    public List<Employee> getAll() {
        return employeeRepository.findAll();

    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee id not found"));
    }

    public List<Employee> getByRoleId(Long id) {
        return employeeRepository.findByUserRole(roleService.getById(id).getId());
    }

    public List<Employee> getByClassId(Long id) {
        return employeeRepository.findByClassroom_Id(id);
    }

    public Employee create(Employee employee) {
        if (!(employee.getPhone().length() >= 11 && employee.getPhone().length() <= 13)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Phone number minimum 11 character and maximum 13!"
            );
        }
        for (Employee employeeCheck : getAll()) {
            if (Objects.equals(employeeCheck.getPhone(), employee.getPhone())) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number is already registered!"
                );
            }
        }
        try {
            Integer.parseInt(employee.getPhone());
        } catch (ResponseStatusException responseStatusException) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Phone number not a number!"
            );
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Long id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
