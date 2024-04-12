package tech.getarrays.employeemanager.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.dtos.EmployeeDtoRequest;
import tech.getarrays.employeemanager.dtos.EmployeeDtoResponse;
import tech.getarrays.employeemanager.exception.EmployeeNotFoundException;
import tech.getarrays.employeemanager.mapper.Mapper;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;
    private final Mapper<Employee, EmployeeDtoResponse> mapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, Mapper<Employee, EmployeeDtoResponse> mapper) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDtoResponse addEmployee(EmployeeDtoRequest employeeDtoRequest){
     employeeDtoRequest.setEmployeeCode(UUID.randomUUID().toString());

        Employee employee = Employee.builder()
                .name(employeeDtoRequest.getName())
                .email(employeeDtoRequest.getEmail())
                .jobTitle(employeeDtoRequest.getJobTitle())
                .phone(employeeDtoRequest.getPhone())
                .imageUrl(employeeDtoRequest.getImageUrl())
                .employeeCode(employeeDtoRequest.getEmployeeCode())
                .build();
      employeeRepo.save(employee);
     return mapper.map(employee);
    }
    @Override

    public List<EmployeeDtoResponse> findAllEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
    return mapper.mapAll(employeeList);
    }
    @Override

    public EmployeeDtoResponse updateEmployee(Long id, EmployeeDtoRequest employeeDtoRequest) throws EmployeeNotFoundException {
        Employee employeeInDB = employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id %s not found".formatted(id)));

        Employee employee = Employee.builder()
                .id(employeeInDB.getId())
                .name(employeeDtoRequest.getName())
                .email(employeeDtoRequest.getEmail())
                .jobTitle(employeeDtoRequest.getJobTitle())
                .phone(employeeDtoRequest.getPhone())
                .imageUrl(employeeDtoRequest.getImageUrl())
                .employeeCode(employeeInDB.getEmployeeCode())
                .build();
        employeeRepo.save(employee);
        return mapper.map(employee);
    }
    @Override

    @Transactional
    public void deleteEmployee(Long id){
         employeeRepo.deleteEmployeeById(id);
    }
    @Override

    public Optional<EmployeeDtoResponse> findEmployeeById(Long id) throws EmployeeNotFoundException {
       return Optional.ofNullable(employeeRepo.findEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException("User by id " + id + "was not found")));
    }



}
