package com.example.companyemployeespring.service;

import com.example.companyemployeespring.model.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Company> findAll(){
        List<Company> all = companyRepository.findAll();
        return all;
    }


    public void save(Company company) {
        companyRepository.save(company);
    }

    public void deleteById(int id) {
        companyRepository.deleteById(id);

    }

    public void deleteAllByCompanyId(int id) {
        employeeRepository.deleteAllByCompanyId(id);
    }

    public void updateForAddEmployee(int companyId) {
        Company company = companyRepository.getById(companyId);
        int size = company.getSize();
        company.setSize(size++);
        companyRepository.save(company);
    }
}
