package br.com.edreis.employeemanager.controller;

import br.com.edreis.employeemanager.model.Employee;
import br.com.edreis.employeemanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(this.service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.findEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.addEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee newEmployee) {
        return ResponseEntity.ok(this.service.updateEmployee(id, newEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        this.service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
