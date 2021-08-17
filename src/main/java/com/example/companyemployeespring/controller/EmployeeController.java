package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Company;
import com.example.companyemployeespring.model.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employers")
    public String getEmployeers(ModelMap modelMap) {
        List<Employee> all = employeeRepository.findAll();
        modelMap.addAttribute("employers", all);
        return "employers";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(ModelMap modelMap) {
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("companies", all);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        Company company = companyRepository.getById(employee.getCompanyId());
        int companyNewSize = company.getSize();
        companyNewSize++;
        company.setSize(companyNewSize);
        companyRepository.save(company);

        return "redirect:/employers";
    }

}
