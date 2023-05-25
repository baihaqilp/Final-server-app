package id.co.metrodata.serverApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.Employee;
import id.co.metrodata.serverApp.models.dto.request.UserRequest;
import id.co.metrodata.serverApp.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/profile")
    public Employee getProile() {
        return employeeService.getProfile();
    }

    @GetMapping("/class/{id}")
    public List<Employee> getByClass(@PathVariable Long id) {
        return employeeService.getByClassId(id);
    }

    @GetMapping("/role/{id}")
    public List<Employee> getByRoleId(@PathVariable Long id) {
        return employeeService.getByRoleId(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return employeeService.update(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }
}
