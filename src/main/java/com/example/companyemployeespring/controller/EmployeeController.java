package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Company;
import com.example.companyemployeespring.model.Employee;
import com.example.companyemployeespring.service.CompanyService;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;
    @GetMapping("/employeesAll")
    public String employees(ModelMap modelMap) {
            List<Employee> employees = employeeService.findAll();
            modelMap.addAttribute("employees", employees);
            return "employers";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        modelMap.addAttribute("companies", companies);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployeePost(@ModelAttribute Employee employee) {
        int companyId = employee.getCompanyId();
        companyService.updateForAddEmployee(companyId);
        employeeService.save(employee);
        return "redirect:/employeesAll";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employeesAll";
    }


}