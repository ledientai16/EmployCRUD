package org.example.employcrud.controller;

import org.example.employcrud.entity.Employee;
import org.example.employcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employ-list")
    public String getEmployee(Model theModel) {
        theModel.addAttribute("employees", employeeService.findAll());
        return "/employ-list";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("theEmployee", new Employee());

        return "employ-form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(
            @ModelAttribute("theEmployee") Employee theEmployee
    ){
        theEmployee = employeeService.save(theEmployee);
        if (theEmployee != null) {
            return "redirect:/employees/employ-list";
        }
        return "employ-form";
    }
}
