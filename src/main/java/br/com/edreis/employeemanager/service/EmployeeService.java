package br.com.edreis.employeemanager.service;

import br.com.edreis.employeemanager.exception.NotFoundException;
import br.com.edreis.employeemanager.model.Employee;
import br.com.edreis.employeemanager.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee addEmployee(Employee employee) {

        LOGGER.info("New employee added. Employee: {}.", employee);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> optEmployee = repository.findById(id);
        if (optEmployee.isPresent()) {
            return optEmployee.get();
        } else {
            throw new NotFoundException("The Employee Id was not found.");
        }
    }

    public Employee updateEmployee(Long id, Employee employee) {
        this.findEmployeeById(id);
        employee.setId(id);
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
