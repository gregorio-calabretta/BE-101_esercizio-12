package tech.getarrays.employeemanager.mapper;

import org.springframework.stereotype.Component;
import tech.getarrays.employeemanager.dtos.EmployeeDtoResponse;
import tech.getarrays.employeemanager.model.Employee;
@Component
public class EmployeeMapper implements Mapper<Employee, EmployeeDtoResponse> {

    public EmployeeDtoResponse map(Employee employee) {
        return new EmployeeDtoResponse(employee.getId(), employee.getName(),employee.getEmail(),employee.getJobTitle(),employee.getPhone(), employee.getImageUrl(),employee.getEmployeeCode());
    }

}