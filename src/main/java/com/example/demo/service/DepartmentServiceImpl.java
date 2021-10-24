package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department= departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department not available");
        }

        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(long departmentId, Department department) {
        Department dp = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName()))
            dp.setDepartmentName(department.getDepartmentName());

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress()))
            dp.setDepartmentAddress(department.getDepartmentAddress());

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode()))
            dp.setDepartmentCode(department.getDepartmentCode());

        return departmentRepository.save(dp);

    }

    @Override
    public Department getDepartmentByName(String departmentName) {
       // return departmentRepository.findByDepartmentName(departmentName);
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
