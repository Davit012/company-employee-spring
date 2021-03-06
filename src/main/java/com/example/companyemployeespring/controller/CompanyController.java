package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Company;
import com.example.companyemployeespring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/companies")
    public String getCompanies(ModelMap modelMap){
        List<Company> all = companyService.findAll();
        modelMap.addAttribute("companies" , all);
        return "companies";
    }
    @GetMapping("/addCompany")
    public String addCompany(){
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/deleteCompany/{id}")
    @Transactional
    public  String deleteCompany(@PathVariable("id") int id ){
        System.out.println(id);
        companyService.deleteAllByCompanyId(id);
        companyService.deleteById(id);
        return "redirect:/companies";
    }
}
