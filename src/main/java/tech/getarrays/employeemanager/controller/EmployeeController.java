package tech.getarrays.employeemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.dtos.EmployeeDtoRequest;
import tech.getarrays.employeemanager.dtos.EmployeeDtoResponse;
import tech.getarrays.employeemanager.exception.EmployeeNotFoundException;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployees(){
        List<EmployeeDtoResponse> employeesDto = employeeService.findAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EmployeeDtoResponse>> getEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        Optional<EmployeeDtoResponse> employeeDto = employeeService.findEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDtoResponse> addEmployee(@RequestBody EmployeeDtoRequest employeeDtoRequest) {
        EmployeeDtoResponse newEmployee = employeeService.addEmployee(employeeDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDtoRequest employeeDtoRequest) throws EmployeeNotFoundException {
        EmployeeDtoResponse updateEmployee = employeeService.updateEmployee(id, employeeDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id")Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }








}
