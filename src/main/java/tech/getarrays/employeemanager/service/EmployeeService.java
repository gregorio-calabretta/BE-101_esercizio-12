package tech.getarrays.employeemanager.service;

import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.dtos.EmployeeDtoRequest;
import tech.getarrays.employeemanager.dtos.EmployeeDtoResponse;
import tech.getarrays.employeemanager.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    public EmployeeDtoResponse addEmployee(EmployeeDtoRequest employeeDtoRequest);
    public List<EmployeeDtoResponse> findAllEmployees();
    public EmployeeDtoResponse updateEmployee(Long id, EmployeeDtoRequest employeeDtoRequest) throws EmployeeNotFoundException;
    public void deleteEmployee(Long id);
    public Optional<EmployeeDtoResponse> findEmployeeById(Long id) throws EmployeeNotFoundException;

}
